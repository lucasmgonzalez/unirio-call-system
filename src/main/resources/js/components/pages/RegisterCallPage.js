const Controller = ($scope, $state, apiService) => {
    $scope.loading = false;
    
    $scope.data = {
        name: '',
        initials: '',
        opensAt: '',
        closesAt: '',
    }

    $scope.register = () => {
        if ($scope.loading) return; // Disable when loading;
        
        // Front-end validation

        $scope.loading = true;
        
        // Format data
        const opensAt = new Date($scope.data.opensAt);
        $scope.data.opensAt = `${opensAt.getFullYear()}-${opensAt.getMonth() + 1}-${opensAt.getDate()} 00:00:00`;
        
        const closesAt = new Date($scope.data.closesAt);
        $scope.data.closesAt = `${closesAt.getFullYear()}-${closesAt.getMonth() + 1}-${closesAt.getDate()} 23:59:59`

        apiService
            .post(`api/call`, Object.assign({}, $scope.data, {collegeSectionId: $scope.$ctrl.section.id}))
            .then(() => {
                $scope.loading = false;
                $state.go('app.home');
            });
    };
}

const template = `
    <form>
        <div className="input-field">
            <input type="text" name="name" ng-model="data.name" placeholder="Nome"/>
        </div>
        <div className="input-field">
            <input type="text" name="initials" ng-model="data.initials" placeholder="Sigla"/>
        </div>
        <div className="input-field">
            <input type="date" name="opensAt" ng-model="data.opensAt" placeholder="Data de abertura"/>
        </div>
        <div className="input-field">
            <input type="date" name="closesAt" ng-model="data.closesAt" placeholder="Data de fechamento"/>
        </div>
        <input type="submit" class="btn" ng-click="register()" ng-disabled="{{ loading }}" value="{{ loading ? 'Enviando...' : 'Enviar'}}"/>
    </form>
`;

module.exports = {
    bindings: {
        section: '<'
    },
    template: template,
    controller: ['$scope', '$state', 'apiService', Controller]
}