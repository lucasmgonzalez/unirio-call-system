import angular from 'angular';
import uirouter from '@uirouter/angularjs';

import registerRoutes , { states } from './routes';
import apiService from './services/api';

import App from './components/App';
import HomePage from './components/pages/HomePage';
import LoginPage from './components/pages/LoginPage';
import RegisterUserPage from './components/pages/RegisterUserPage';
import ResetPasswordPage from './components/pages/ResetPasswordPage';
import RegisterCollegeSection from './components/pages/RegisterCollegeSection';
import EditCollegeSection from './components/pages/EditCollegeSection';
import RegisterCallPage from './components/pages/RegisterCallPage';

const myApp = angular.module('myApp', [uirouter])
    .service('apiService', ['$rootScope', apiService])
    .run([
        "$state", "$urlRouter",
        function($state){
            /* const currentRoute = states.find(state => `#!/${state.url}` === window.location.hash); */
            $state.go('app.home');
        }
    ]);

// Setting Config
myApp.config(($stateProvider, $provide) => {
    registerRoutes($stateProvider);
});

myApp.component('appPage', App);
myApp.component('loginPage', LoginPage);
myApp.component('homePage', HomePage);
myApp.component('registerUserPage', RegisterUserPage);
myApp.component('resetPasswordPage', ResetPasswordPage);
myApp.component('registerCollegeSection', RegisterCollegeSection);
myApp.component('editCollegeSection', EditCollegeSection);
myApp.component('registerCallPage', RegisterCallPage);

