import angular from 'angular';
import uirouter from '@uirouter/angularjs';

const myApp = angular.module('myApp', [uirouter]);

myApp.config($stateProvider => {
    const indexState = {
        name: 'index',
        url: '/',
        component: '<app></app>'
    };

    $stateProvider.state(indexState);
});

myApp.component('app', {
    template: `It worked!`,
});

