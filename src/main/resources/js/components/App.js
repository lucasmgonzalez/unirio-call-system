const Controller = ($scope, $rootScope, apiService) => {
    $scope.$watch(() => $rootScope.authUser, value => {
        $scope.authenticated = !!value;
        if (value) {
            $scope.name = value.user.name;
        }
    }, true);

    $scope.logout = apiService.logout;
};

const template = `
    <nav>
        <div class="nav-wrapper">
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a ui-sref="app.home" ui-sref-active="active">Home</a></li>
                <li ng-show="!authenticated"><a ui-sref="app.register-user">Cadastrar</a></li>
                <li ng-show="!authenticated"><a ui-sref="app.login" ui-sref-active="active">Login</a></li>
                <li ng-show="authenticated"><a ng-bind="name"></a></li>
                <li ng-show="authenticated"><a ng-click="logout()">Sair</a></li>
                <li></li>
            </ul>
        </div>
    </nav>    

    <div class="container">
        <ui-view></ui-view>
    </div>
`;

module.exports = {
    template: template,
    controller: ['$scope', '$rootScope', 'apiService', Controller]
}
