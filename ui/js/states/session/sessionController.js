angular.module('flightApp').controller('sessionController', ['userDataService', '$state',
    function (userDataService, $state) {

        this.userDataService = userDataService

        this.search = ''

        this.submitSearch = (searchType) => {
            let resultPool = []

            let arrayOfUsernames = []
            let arrayOfHashtags = []
            let arrayOfSearchWords = this.search.split(' ')

            arrayOfSearchWords.forEach((word) => {
                if (word.charAt(0) !== '#' && word.charAt(0) !== '@') {
                    if(word !== ''){
                        arrayOfUsernames.push(word)
                    }
                } else if (word.charAt(0) === '@') {
                    let username = word.slice(1)
                    arrayOfUsernames.push(username)
                } else {
                    let label = word.slice(1)
                    arrayOfHashtags.push(label)
                }
            })

            if (searchType === 'USER') {
                arrayOfUsernames.forEach((username) => {
                    // Get users matching the username
                    // Adds each user to resultPool
                    userListService.getUser(username).then((succeedResponse) => {
                        if(succeedResponse.data) {
                            resultPool.push(succeedResponse.data)
                        }
                    })
                })

                this.search = ''
                this.goToCustomUserList(resultPool)
            } else if (searchType === 'TWEET') {
                arrayOfUsernames.forEach((username) => {
                    // Get tweets by mention
                    // Adds each tweet to resultPool
                    feedService.getTweets(username).then((succeedResponse) => {
                        resultPool.push(...succeedResponse.data)
                    })
                })

                arrayOfHashtags.forEach((hashtag) => {
                    // Get tweets by tag
                    // Adds each tweet to resultPool
                    feedService.getTweetsByHashtag(hashtag).then((succeedResponse) => {
                        resultPool.push(...succeedResponse.data)
                    })
                })

                this.search = ''
                this.goToCustomFeed(resultPool)
            }
        }

        


        // this.goToCustomFeed = (resultPool) => {
        //     userDataService.activeFeed = userDataService.feedTypeEnum.CUSTOM
        //     userDataService.feedDependency = resultPool
        //     userDataService.reloadIfNecessary('session.feed', 'Search Result ')
        // }




        this.viewFlights = () => {
            userDataService.reloadIfNecessary('session.flight')
        }

        this.search = () => {
            userDataService.reloadIfNecessary('session.search')
        }

        this.viewHistory = () => {
            userDataService.reloadIfNecessary('session.history')
        }

        this.logout = () => {
            userDataService.logout()
            userDataService.reloadIfNecessary('title.login')
        }

        if (userDataService.loggedIn()) {
            
            // userListService.getFollowers().then((succeedResponse) => {
            //     userDataService.followersNum = succeedResponse.data.length
            // })

            // userListService.getFollowing().then((succeedResponse) => {
            //     userDataService.followingNum = succeedResponse.data.length
            // })
        } else {
            // $state.go('title.login')
        }

    }
])