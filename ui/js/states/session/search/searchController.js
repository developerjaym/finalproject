angular.module('flightApp').controller('searchController', ['searchService', 'userDataService', '$state',
    function (searchService, userDataService, $state) {

        this.content = new Query("MEMPHIS", "NASHVILLE")//filler

        this.displayUhOh = false;

        this.search = () =>{
            if(this.content.to === this.content.from)
                alert("You can't go from yourself to yourself");
            searchService.search(this.content.from, this.content.to).then((succeedResponse) => {
                //this.itineraries.push(succeedResponse.data)
                userDataService.searchResults = succeedResponse.data;
                this.displayUhOh = false;
                userDataService.reloadIfNecessary('session.history');//session.search
            }, (error)=>{
                //alert("No such flights available")
                this.displayUhOh = true;
            })  
            
        }

        if (!userDataService.loggedIn()) {
            $state.go('title.login')
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