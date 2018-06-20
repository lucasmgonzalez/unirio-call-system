const Controller = ($scope, $state, apiService) => {
    $scope.loading = false;
    
    $scope.data = {
        name: '',
        initials: '',
    }

    $scope.register = () => {
        if ($scope.loading) return; // Disable when loading;
        
        // Front-end validation

        $scope.loading = true;
        
        apiService
            .post('api/college-section', $scope.data)
            .then(() => {
                $scope.loading = false;
                $state.go('app.home');
            });
    };
}

const template = `
    <form>
        <div class="input-field">
            <input type="text" name="name" ng-model="data.name" placeholder="Nome"/>
        </div>
        <div class="input-field">
            <input type="text" name="initials" ng-model="data.initials" placeholder="Sigla"/>
        </div>
        <input type="submit" class="btn" ng-click="register()" ng-disabled="{{ loading }}" value="{{ loading ? 'Enviando...' : 'Enviar'}}"/>
    </form>
`;

module.exports = {
    template: template,
    controller: ['$scope', '$state', 'apiService', Controller]
}