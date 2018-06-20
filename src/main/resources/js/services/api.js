import axios from 'axios';

const axiosInstance = axios.create({
    baseUrl: 'http://localhost:8080/',
});

const AUTH_USER_KEY = "auth_user";

axiosInstance.interceptors.request.use(config => {
    const authUser = JSON.parse(window.localStorage.getItem(AUTH_USER_KEY));
    if (authUser) {
        config.headers['Authorization'] = `${authUser.token}`;
    }

    return config;
});

export const ApiService = {
    get: (path) => axiosInstance.get(path).then(response => {
        if (response.status === 200) {
            return response.data;
        }
    }),

    post: (path, data) => axiosInstance.post(path, data || {}).then(response => {
        if (response.status === 200 || response.status === 201) {
            return response.data;
        }
    }),
    
    put: (path, data) => axiosInstance.put(path, data || {}).then(response => {
        if (response.status === 200) {
            return response.data;
        }
    }),
    
    // Unfortunately, delete is a reserved word
    delete: (path, data) => axiosInstance.delete(path, data || {}).then(response => {
        if (response.status === 200) {
            return response.data;
        }
    }),

    setAuthenticated: authUser => {
        window.localStorage.setItem(AUTH_USER_KEY, JSON.stringify(authUser));
    },

    getAuthenticated: () => {
        return JSON.parse(window.localStorage.getItem(AUTH_USER_KEY)) || null;
    },

    isAuthenticated: () => {
        return !!window.localStorage.getItem(AUTH_USER_KEY);
    }
}

const AngularApiService = function($rootScope) {
    
    $rootScope.authUser = ApiService.getAuthenticated();
    
    const setAuthenticated = (authUser) => {
        ApiService.setAuthenticated(authUser);

        $rootScope.authUser = authUser;
        $rootScope.$apply();
    }

    const login = (username, password) => 
        axiosInstance.post('login',{
            username: username,
            password: password
        }).then(response => {
            if (response.status === 200) {
                const token = response.headers['authorization'];
                
                return axiosInstance.get('api/me', { headers: { Authorization: token } })
                    .then(({ status, data: user }) => {
                        if (status !== 200) {
                            alert("Erro ao recuperar o usuário logado");
                            return;
                        }
                        const authUser = {
                            token,
                            user
                        }

                        setAuthenticated(authUser);
                    });

            }
        });

    const logout = () => {
        setAuthenticated(false);
    }
    
    return {
        get: ApiService.get,
        post: ApiService.post,
        put: ApiService.put,
        delete: ApiService.delete,
        login,
        logout,
        getAuthenticated: ApiService.getAuthenticated,
        isAuthenticated: ApiService.isAuthenticated
    }
}

export default AngularApiService;