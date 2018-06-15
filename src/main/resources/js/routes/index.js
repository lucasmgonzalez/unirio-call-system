const indexState = {
    name: 'index',
    url: '',
    component: 'helloWorld'
};

const registerRoutes = $stateProvider => {
    $stateProvider.state(indexState);
}

export default registerRoutes;
