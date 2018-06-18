const states = [
    {
        name: 'app',
        url: '',
        component: 'appPage'
    },
    {
        name: 'app.home',
        url: 'home',
        component: 'homePage'
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
    }
];

const registerRoutes = $stateProvider => {
    
    states.forEach(state => {
        $stateProvider.state(state);
    });
}

export default registerRoutes;
