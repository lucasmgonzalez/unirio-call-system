const Controller = $scope => {
    $scope.name = 'Luke';
    
    $scope.greeting = (name) => `Hello, ${name}!`;

    $scope.grabNewName = () => {
        const name = prompt('Novo nome...');

        $scope.name = name;
    };
};

const template = `
    <h3 ng-click="grabNewName()">{{greeting(name)}}</h3>
`;

module.exports = {
    template: template,
    controller: Controller
}
