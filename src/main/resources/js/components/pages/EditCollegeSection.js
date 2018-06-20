const Controller = ($scope, $state, apiService) => {
    $scope.loading = false;

    console.log($scope);

    $scope.data = {
        id: "",
        name: "",
        initials: ""
    };

    $scope.register = () => {
        if ($scope.loading) return; // Disable when loading;
        
        // Front-end validation

        $scope.loading = true;
        
        apiService
            .put(`api/college-section/${$scope.$ctrl.section.id}`, $scope.$ctrl.section)
            .then(() => {
                $scope.loading = false;
                $state.go('app.home');
            });
    };
}

const template = `
    <form>
        <div class="input-field">
            <input type="text" name="name" ng-model="$ctrl.section.name" placeholder="Nome"/>
        </div>
        <div class="input-field">
            <input type="text" name="initials" ng-model="$ctrl.section.initials" placeholder="Sigla"/>
        </div>
        <input type="submit" class="btn" ng-click="register()" ng-disabled="{{ loading }}" value="{{ loading ? 'Enviando...' : 'Enviar'}}"/>
    </form>
`;

module.exports = {
    bindings: { section: '<' },
    template: template,
    controller: ['$scope', '$state', 'apiService', Controller]
}