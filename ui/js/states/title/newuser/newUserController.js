angular.module('flightApp').controller('newUserController', ['newUserService', 'userDataService', '$state',
    function (newUserService, userDataService, $state) {

        // Testing cases vvv
        this.submission = {}
        // this.submission.username = 'testUser22'
        // this.submission.password = 'testUser22'
        // this.submission.firstName = 'testUser'
        // this.submission.lastName = 'testUser'
        // this.submission.email = 'testUser'
        // this.submission.phone = 'testUser'

        this.usernameErrorCss = "black"
        this.passwordErrorCss = "black"
        this.emailErrorCss = "black"
        this.addressErrorCss = "black"
        

        this.createNewCustomer = () => {
            if (this.submission.username !== '' &&
                this.submission.password !== '' &&
                this.submission.email !== '' &&
                this.submission.address !== '') {

                const customer = userDataService.buildCustomer(
                    this.submission.username,
                    this.submission.password,
                    this.submission.email,
                    this.submission.address)

                newUserService.createNewUser(customer).then((succeedResponse) => {
                    // User created, data will contain dto of user without its password
                    $state.go('title.login')
                }, (errorResponse) => {
                    
                        this.usernameErrorCss = "red"
                        this.passwordErrorCss = "red"
                        this.emailErrorCss = "red"
                        this.addressErrorCss = "red"
                })
            }
        }
    }
])