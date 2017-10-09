angular.module('flightApp').controller('searchController', ['searchService', 'userDataService', '$state',
    function (tweetService, userDataService, $state) {

        this.content = new Query("MEMPHIS", "NASHVILLE")
        // this.createNewTweet = () => {
        //     if (this.content.length < 255) {
        //         // new Audio('shriek.mp3').play()
        //         tweetService.createNewTweet(userDataService.buildTweet(this.content)).then((succeedResponse) => {
        //             userDataService.activeFeed = userDataService.feedTypeEnum.MAIN
        //             userDataService.feedDependency = undefined
        //             userDataService.reloadIfNecessary('session.feed', 'My ');
        //         }, (errorResponse) => {
        //             alert('Error: ' + errorResponse.status)
        //         })
        //     } else {
        //         this.content = 'Your Message Is Too Long'
        //     }
        // }

        this.search = () =>{
            alert("To: " + this.content.getTo() + "\nFrom: " + this.content.getFrom());
        }

        if (!userDataService.loggedIn()) {
            // $state.go('title.login')
        }
    }
])
class Query
{
    constructor(to, from)
    {
        this.to = to;
        this.from = from;
    }
    getTo()
    {
        this.to
    }
    setTo(to)
    {
        this.to = to;
    }
    getFrom()
    {
        this.from
    }
    setFrom(from)
    {
        this.from = from;
    }
}