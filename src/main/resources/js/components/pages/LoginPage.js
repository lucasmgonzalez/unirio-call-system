const Controller = ($scope, $state, apiService) => {
    
    
    $scope.loading = false;
    
    $scope.data = {
        username: '',
        password: '',
    }

    $scope.login = () => {
        if ($scope.loading) return; // Disable when loading;
        $scope.loading = true;
        apiService
            .login($scope.data.username, $scope.data.password)
            .then(() => {
                $scope.loading = false;
                $state.go('app.home');
            });
    };
}

const template = `
    <form>
        <div class="input-field">
            <input type="text" name="username" ng-model="data.username" placeholder="Email"/>
        </div>
        <div class="input-field">
            <input type="password" name="password" ng-model="data.password" placeholder="Senha"/>
        </div>
        <div class="row">
            <a ui-sref="app.reset-password" class="btn-flat">Esqueci minha senha</a>
            <input type="submit" class="btn right" ng-click="login()" ng-disabled="{{ loading }}" value="{{ loading ? 'Enviando...' : 'Entrar'}}"/>
        </div>
    </form>
`;

module.exports = {
    template: template,
    controller: ['$scope', '$state', 'apiService', Controller]
}