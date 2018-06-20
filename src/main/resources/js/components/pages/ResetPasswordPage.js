const Controller = ($scope, apiService) => {
    $scope.loading = false;
    
    $scope.data = {
        email: '',
    }

    $scope.resetPassword = () => {
        if ($scope.loading) return; // Disable when loading;
        
        // Front-end validation

        $scope.loading = true;
        
        apiService
            .post('api/user/reset-password', $scope.data)
            .then(() => {
                $scope.loading = false;
            });
    };
}

const template = `
    <form>
        <div class="input-field">
            <input type="email" name="email" ng-model="data.email" placeholder="Email"/>
        </div>
        <input type="submit" class="btn" ng-click="resetPassword()" ng-disabled="{{ loading }}" value="{{ loading ? 'Enviando...' : 'Enviar'}}"/>
    </form>
`;

module.exports = {
    template: template,
    controller: ['$scope', 'apiService', Controller]
}