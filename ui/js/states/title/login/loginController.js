angular.module('flightApp').controller('loginController', ['loginService', 'userDataService', '$state',
    function (loginService, userDataService, $state) {
        this.submission = {}

        this.usernameErrorCss = "black"
        this.passwordErrorCss = "black"

        this.guest = () => {
            this.submission.username = 'username'
            this.submission.password = 'password'
            this.login()
        }

        this.login = () => {
            loginService.login(this.submission).then((succeedResponse) => {
                userDataService.setCustomerCredentials(this.submission.username, this.submission.password)
                this.usernameErrorCss = "black"
                this.passwordErrorCss = "black"

                //userDataService.activeFeed = userDataService.feedTypeEnum.MAIN
                //userDataService.feedDependency = undefined
                userDataService.reloadIfNecessary('session.flight')
            }, (errorResponse) => {
                console.dir(errorResponse.status)
                    this.submission.username = ''
                    this.submission.password = ''
                    this.usernameErrorCss = "red"
                    this.passwordErrorCss = "red"
                

            })
        }

    }
])