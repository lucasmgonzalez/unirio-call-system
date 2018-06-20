import { ApiService } from '../services/api';

export const states = [
    {
        name: 'app',
        url: '',
        component: 'appPage'
    },
    {
        name: 'app.home',
        url: 'home',
        component: 'homePage',
        resolve: {
            sections: () => {
                const authUser = ApiService.getAuthenticated();

                return authUser && authUser.user ? ApiService.get(`api/user/${authUser.user.id}/college-sections`) : [];
            }
        }
    },
    {
        name: 'app.login',
        url: 'signin',
        component: 'loginPage'
    },
    {
        name: 'app.register-user',
        url: 'cadastro',
        component: 'registerUserPage'
    },
    {
        name: 'app.reset-password',
        url: 'esqueci-senha',
        component: 'resetPasswordPage'
    },
    {
        name: 'app.college-section-register',
        url: 'unidade/criar',
        component: 'registerCollegeSection'
    },
    {
        name: 'app.college-section-edit',
        url: 'unidade/:sectionId/editar',
        component: 'editCollegeSection',
        resolve: {
            section: ($stateParams) => ApiService.get(`api/college-section/${$stateParams.sectionId}`)
        }
    },
    {
        name: 'app.call-register',
        url: 'unidade/:sectionId/chamadas/criar',
        component: 'registerCallPage',
        resolve: {
            section: ($stateParams) => ApiService.get(`api/college-section/${$stateParams.sectionId}`)
        }
    }
];

const registerRoutes = ($stateProvider) => {
    states.forEach(state => {
        $stateProvider.state(state);
    });
}

export default registerRoutes;
