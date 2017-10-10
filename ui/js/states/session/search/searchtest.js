class Testflight
{
    constructor(offset, length, origin, destination)
    {
        this.offset = offset;
        this.length = length;
        this.origin = origin;
        this.destination = destination;
        this.flightname = "Flight: " + this.origin + " to " + this.destination;
        this.landingtime =(this.offset+ this.length);
    }
    toString()
    {
        return this.origin + " to " + this.destination + "\n    " + this.offset + " to " + this.landingtime + "\n";
    }
}

const cities = [
    'Chat',
    'Knox',
    'Nash',
    'Mem'
]

const flight1 = new Testflight(8, 3, 'Nash', 'Knox')
const flight2 = new Testflight(7, 2, 'Knox', 'Nash')
const flight3 = new Testflight(9, 3, 'Chat', 'Mem')
const flight4 = new Testflight(3, 1, 'Mem', 'Chat')
const flight5 = new Testflight(1, 3, 'Chat', 'Nash')

const flights = [flight1, flight2, flight3, flight4, flight5];

// console.log(flights)

const origin = 'Mem'
const destination = 'Knox'

const directFlight = flights.reduce((previouslyReturned, currentFlight)=>{

    // console.log("current flight:\n    " + currentFlight)
    if(currentFlight.destination === destination && currentFlight.origin === origin)
        return true
    return previouslyReturned

}, false)


const properOrigins = flights.reduce((previouslyReturned, currentFlight)=>{
    if(currentFlight.origin === origin)
        previouslyReturned.push(currentFlight)
    return previouslyReturned
}
, [])



const properDestinations = flights.reduce((previouslyReturned, currentFlight)=>{
    if(currentFlight.destination === destination)
        previouslyReturned.push(currentFlight)
    return previouslyReturned
}
, [])





const getProperOrigins = (ogn, arr) => {
    return arr.reduce((previouslyReturned, currentFlight)=>
    {
        if(currentFlight.origin === ogn)
            previouslyReturned.push(currentFlight)
        return previouslyReturned
    }
, [])}

const getProperDestinations = (dest, arr) => {
    return arr.reduce((previouslyReturned, currentFlight)=>
    {
        if(currentFlight.destination === dest)
            previouslyReturned.push(currentFlight)
        return previouslyReturned
    }
, [])}

const getDirectFlight = (o, d, f) => {
    return f.reduce((previouslyReturned, currentFlight)=>{
        
            if(currentFlight.destination === d && currentFlight.origin === o)
            {
                return currentFlight
            }    
            return previouslyReturned
        
        }, undefined)
}

const getNextCity = (thisCity, c) =>
{
    const thisIndex = cities.indexOf(thisCity)
    let nextIndex = thisIndex+1;
    console.log("index of " + thisCity + ": " + thisIndex)
    if(thisIndex === c.length-1)
        nextIndex = 0;
    return nextIndex
}

let bestpath = (o, d, f) => {
    // console.log("best path is " + getDirectFlight(o, d, f))
    console.log("o: " + o);
    console.log("d: " + d)
    console.log("f: " + f)
    let withProperOrigins = getProperOrigins(o, f)
    let withProperDestinations = getProperDestinations(d, f)
    if(getDirectFlight(o, d, f))//there is a direct flight
    {

    }
    return getDirectFlight(o, d, f)
}


// console.log("Proper origins\n   " + getProperOrigins(origin, flights))//properOrigins)
// console.log("Proper destinations\n   " + getProperDestinations(destination, flights))
// console.log("Do we have a direct flight? " + directFlight)
console.log("best path: " + bestpath(origin, destination, flights).flightname)
// console.log("index of " + getNextCity(origin, cities))