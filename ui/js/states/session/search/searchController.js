angular.module('flightApp').controller('searchController', ['searchService', 'userDataService', '$state',
    function (searchService, userDataService, $state) {

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

        this.displayUhOh = false;

        this.search = () =>{
            alert("To: " + this.content.to + "\nFrom: " + this.content.from);
            if(this.content.to === this.content.from)
                alert("You can't go from yourself to yourself");
            searchService.search(this.content.from, this.content.to).then((succeedResponse) => {
                //this.itineraries.push(succeedResponse.data)
                userDataService.searchResults = succeedResponse.data;
                this.displayUhOh = false;
                userDataService.reloadIfNecessary('session.history');//session.search
            }, (error)=>{
                //alert("404")
                this.displayUhOh = true;
            })  
            
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