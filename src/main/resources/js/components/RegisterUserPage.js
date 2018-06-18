const Controller = ($scope, apiService) => {
    $scope.loading = false;
    
    $scope.data = {
        name: '',
        email: '',
        password: '',
        passwordConfirmation: '',
    }

    $scope.register = () => {
        if ($scope.loading) return; // Disable when loading;
        
        // Front-end validation

        $scope.loading = true;
        
        apiService
            .post('api/user', $scope.data)
            .then(() => {
                $scope.loading = false;
                apiService.login($scope.email, $scope.password).then(() => {
                    console.log('success');
                    // redirect home
                });
            });
    };
}

const template = `
    <form>
        <div className="input-field">
            <input type="text" name="name" ng-model="data.name" placeholder="Nome"/>
        </div>
        <div className="input-field">
            <input type="email" name="email" ng-model="data.email" placeholder="Email"/>
        </div>
        <div className="input-field">
            <input type="password" name="password" ng-model="data.password" placeholder="Senha"/>
        </div>
        <div className="input-field">
            <input type="password" name="passwordConfirmation" ng-model="data.passwordConfirmation" placeholder="Confirmar senha"/>
        </div>
        <input type="submit" class="btn" ng-click="register()" ng-disabled="{{ loading }}" value="{{ loading ? 'Enviando...' : 'Enviar'}}"/>
    </form>
`;

module.exports = {
    template: template,
    controller: ['$scope', 'apiService', Controller]
}