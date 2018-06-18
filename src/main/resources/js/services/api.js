import axios from 'axios';

const AuthService = function($rootScope) {
    const AUTH_USER_KEY = "auth_user";
    
    const isAuthenticated = () => {
        const authUser = window.localStorage.getItem(AUTH_USER_KEY);

        return !!authUser;
    }

    const getAuthenticated = () => {
        return window.localStorage.getItem(AUTH_USER_KEY) || null;
    }
    
    const setAuthenticated = (token, isAdmin) => {
        const authUser = {
            token,
            isAdmin
        }
        $rootScope.authUser = authUser;
        $rootScope.$apply();
    }

    $rootScope.authUser = getAuthenticated();

    const axiosInstance = axios.create({
        baseUrl: 'http://localhost:8080/',
    })

    axiosInstance.interceptors.request.use(config => {
        const authUser = window.localStorage.getItem(AUTH_USER_KEY);
        if (authUser) {
            config.headers['Authorization'] = `${authUser.token}`;
        }

        return config;
    });

    const get = (path) => axiosInstance.get(path).then(response => {
        if (response.status === 200) {
            return response.data;
        }
    });

    const post = (path, data) => axiosInstance.post(path, data || {}).then(response => {
        if (response.status === 200 || response.status === 201) {
            return response.data;
        }
    });

    const put = (path, data) => axiosInstance.put(path, data || {}).then(response => {
        if (response.status === 200) {
            return response.data;
        }
    });

    // Unfortunately, delete is a reserved word
    const deleteRequest = (path, data) => axiosInstance.delete(path, data || {}).then(response => {
        if (response.status === 200) {
            return response.data;
        }
    })

    const login = (username, password) => 
        axiosInstance.post('login',{
            username: username,
            password: password
        }).then(response => {
            if (response.status === 200) {
                const authUser = {
                    token: response.headers['authorization'],
                    isAdmin: response.headers['authenticated-isadmin'] === "1",
                };
                window.localStorage.setItem(AUTH_USER_KEY, authUser);
                setAuthenticated(authUser);
            }
        });
    
    return {
        get,
        post,
        put,
        delete: deleteRequest,
        login,
    }
}

export default AuthService;