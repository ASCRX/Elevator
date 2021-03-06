public class Elevator {
    public static final int MAXIMUM_CAPACITY = 10;
    public static final int NUMBER_OF_FLOORS = 7;

    enum DirectionOfTravel {
        UP, DOWN, STOPPED
    }

    public enum Floor {
        FIRST("FIRST", 0, false),
        SECOND("SECOND", 0, false),
        THIRD("THIRD", 0, false),
        FOURTH("FOURTH", 0, false),
        FIFTH("FIFTH", 0, false),
        SIXTH("SIXTH", 0, false),
        SEVENTH("SEVENTH", 0, false);

        private final int _number;
        private final String _name;
        private int _queuedPassengers;
        private boolean _hasDestinationRequests;
        private Floor _nextUp;
        private Floor _nextDown;

        Floor(String name, int queuedPax, boolean hasDestinationRequests) {
            this._number = this.ordinal() + 1;
            this._name = name;
            this._queuedPassengers = queuedPax;
            this._hasDestinationRequests = hasDestinationRequests;
        }

        public int floorNumber() {
            return _number;
        }

        public String floorName() {
            return _name;
        }

        public int queuedPassengers() {
            return _queuedPassengers;
        }

        public void addQueuedPassenger(){
            this._queuedPassengers +=1;
        }

        public void clearQueuedPassengers(){
            this._queuedPassengers = 0;
        }

        public Floor nextFloorUp() {
            Floor next = Floor.FIRST; // default
            switch(_number){
                case 1:  next =  Floor.SECOND;
                    break;
                case 2:  next = Floor.THIRD;
                    break;
                case 3:  next = Floor.FOURTH;
                    break;
                case 4:  next =  Floor.FIFTH;
                    break;
                case 5:  next = Floor.SIXTH;
                    break;
                case 6:  next =  Floor.SEVENTH;
                    break;

            }
            return next;
        }

        public Floor nextFloorDown() {
            Floor next = Floor.FIRST; // default
            switch(_number){
                case 2:  next = Floor.FIRST;
                    break;
                case 3:  next = Floor.SECOND;
                    break;
                case 4:  next =  Floor.THIRD;
                    break;
                case 5:  next = Floor.FOURTH;
                    break;
                case 6:  next =  Floor.FIFTH;
                    break;
                case 7:  next = Floor.SIXTH;
                    break;

            }
            return next;

        }

        public void makeDestinationRequest() {
            this._hasDestinationRequests = true;
        }

        public void clearDestinationRequest() {
            this._hasDestinationRequests = false;
        }

        public boolean hasDestinationRequests(){
            return this._hasDestinationRequests;
        }


    }


    private DirectionOfTravel _directionOfTravel;
    private Floor _currentFloor;
    private int _passengersOnboard;
    private java.util.HashMap _floorQueue;

    Elevator() {
        _currentFloor = Floor.FIRST;
        _passengersOnboard = 0;
        _directionOfTravel = DirectionOfTravel.UP;

    }

    public void move() {

        if (_currentFloor == Floor.FIRST) {
            _directionOfTravel = DirectionOfTravel.UP;
        }
        if (_currentFloor == Floor.SEVENTH) {
            _directionOfTravel = DirectionOfTravel.DOWN;
        }


        if (_directionOfTravel == DirectionOfTravel.UP) {
            _currentFloor = _currentFloor.nextFloorUp();
        } else if (_directionOfTravel == DirectionOfTravel.DOWN) {
           _currentFloor = _currentFloor.nextFloorDown();
        }

        if(_currentFloor.hasDestinationRequests()){
            stop();
        } 

    }

    private void stop() {
        System.out.println("Stopping on "+ _currentFloor.floorName() +" floor " );
        _currentFloor.clearDestinationRequest();
        _passengersOnboard = _passengersOnboard - _currentFloor.queuedPassengers();
        _currentFloor.clearQueuedPassengers();
        System.out.println(this);

    }

    public void boardPassenger(int floor){
        _passengersOnboard +=1;
        switch(floor){
            case 1: Floor.FIRST.makeDestinationRequest();
                Floor.FIRST.addQueuedPassenger();
                break;
            case 2: Floor.SECOND.makeDestinationRequest();
                Floor.SECOND.addQueuedPassenger();
                break;
            case 3: Floor.THIRD.makeDestinationRequest();
                Floor.THIRD.addQueuedPassenger();
                break;
            case 4: Floor.FOURTH.makeDestinationRequest();
                Floor.FOURTH.addQueuedPassenger();
                break;
            case 5: Floor.FIFTH.makeDestinationRequest();
                Floor.FIFTH.addQueuedPassenger();
                break;
            case 6: Floor.SIXTH.makeDestinationRequest();
                Floor.SIXTH.addQueuedPassenger();
                break;
            case 7: Floor.SEVENTH.makeDestinationRequest();
                Floor.SEVENTH.addQueuedPassenger();
                break;
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append("Currently " + _passengersOnboard + " Passengers Onboard\r\n");
        output.append("On Floor   : " + _currentFloor + "\r\n");
        return output.toString();

    }

    public static void main(String[] args) {
        Elevator theElevator = new Elevator();

        theElevator.boardPassenger(2);
        theElevator.boardPassenger(3);

        System.out.println(theElevator);

        for(int i=1;i<21;i++){
        theElevator.move();
        }
    
    }

}
