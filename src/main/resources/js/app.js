import angular from 'angular';
import uirouter from '@uirouter/angularjs';

import registerRoutes from './routes';
import apiService from './services/api';

import App from './components/App';
import HomePage from './components/HomePage';
import LoginPage from './components/LoginPage';
import RegisterUserPage from './components/RegisterUserPage'

const myApp = angular.module('myApp', [uirouter]).service('apiService', ['$rootScope', apiService]);

// Setting Config
myApp.config(($stateProvider, $provide) => {
    registerRoutes($stateProvider);
});

myApp.component('appPage', App);
myApp.component('loginPage', LoginPage);
myApp.component('homePage', HomePage);
myApp.component('registerUserPage', RegisterUserPage);

