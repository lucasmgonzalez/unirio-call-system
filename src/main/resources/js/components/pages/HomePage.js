const Controller = ($scope, $rootScope) => {
    $scope.isAuthenticated = !!$rootScope.authUser;
    $scope.isAdmin = $scope.isAuthenticated && $rootScope.authUser.user ? $rootScope.authUser.user.administrator : false;

};

const template = `
    <h2>Sistema de chamadas</h1>

    <div>
        <ul class="collection with-header">
            <li class="collection-header">Chamadas Abertas</li>
        </ul>
    </div>

    <div>
        <ul class="collection with-header">
            <li class="collection-header">Chamadas Fechadas</li>
        </ul>
    </div>

    <div ng-show="isAuthenticated && isAdmin">
        <ul class="collection with-header">
            <li class="collection-header">
                Suas unidades
                <a ui-sref="app.college-section-register" class="right"><i class="material-icons">add</i></a>
            </li>
            <li  ng-repeat="section in $ctrl.sections" class="collection-item">
                {{section.name}}
                <a ui-sref="app.college-section-edit({sectionId: section.id})" class="right"><i class="material-icons">edit</i></a>
                <a ui-sref="app.call-register({sectionId: section.id})" class="right"><i class="material-icons">assignment</i></a>
            </li>
        </ul>
    </div>
`;

module.exports = {
    bindings: { sections: '<' },
    template: template,
    controller: ['$scope', '$rootScope', Controller]
}
