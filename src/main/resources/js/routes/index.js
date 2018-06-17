const indexState = {
    name: 'index',
    url: '',
    component: 'homePage'
};

const registerRoutes = $stateProvider => {
    $stateProvider.state(indexState);
}

export default registerRoutes;
