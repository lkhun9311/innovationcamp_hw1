import java.util.UUID;

public class Bus {
    private int max_passenger; // 버스의 탑승 가능한 최대 승객 인원 수
    private int current_passenger; // 버스의 탑승 가능한 현재 승객 인원 수
    private int billing; // 버스의 잔고
    private final String bus_number; // 버스 번호;
    private int fuel; // 주유량
    private int current_speed; // 버스의 현재 속도
    private BusStatus bus_status; // 버스의 운행 상태

    public Bus(int max_passenger, int current_passenger, int billing, int fuel, int current_speed){
        this.max_passenger = max_passenger;
        this.current_passenger = current_passenger;
        this.billing = billing;
        this.bus_number = UUID.randomUUID().toString(); // UUID를 활용해 버스 번호에 고유한 값을 입력
        this.fuel = fuel;
        this.current_speed = current_speed;
        this.bus_status = BusStatus.RUN;
    }

    // getter, setter
    public int getMax_passenger() {
        return max_passenger;
    }

    public int getCurrent_passenger() {
        if(this.bus_status.equals(BusStatus.RESET)|this.bus_status.equals(BusStatus.GARAGE)){
            current_passenger = 0;
        }
        return current_passenger;
    }

    public void setCurrent_passenger(int current_passenger) {
        this.current_passenger += current_passenger;
        if(this.current_passenger>this.getMax_passenger()){
            System.out.println("");
            System.err.println("최대 승객 수 초과");
            System.out.println("");
        } else if (this.current_passenger<0) {
            System.out.println("");
            System.err.println("5XX 서버 에러 ");
            System.err.println("승객 수는 음수가 될 수 없습니다.");
            System.out.println("");
        }
        if(this.bus_status.equals(BusStatus.RESET)|this.bus_status.equals(BusStatus.GARAGE)){
            this.current_passenger = 0;
        }
    }

    public int getBilling() {
        return billing;
    }

    public void setBilling(int billing) {
        this.billing += billing;
        if(this.bus_status.equals(BusStatus.RESET)){
            this.billing = 0;
        }
    }

    public String getBus_number() {
        return bus_number;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel += fuel;
        if(this.bus_status.equals(BusStatus.RESET)){
            this.fuel = 100;
        }
    }

    public int getCurrent_speed() {
        if(this.bus_status.equals(BusStatus.RESET)|this.bus_status.equals(BusStatus.GARAGE)){
            current_speed = 0;
        }
        return current_speed;
    }

    public void setCurrent_speed(int current_speed) {
        this.current_speed += current_speed;
        if(this.bus_status.equals(BusStatus.RESET)|this.bus_status.equals(BusStatus.GARAGE)){
            this.current_speed = 0;
        }
    }

    public BusStatus getBus_status() {
        return bus_status;
    }

    public void setBus_status(BusStatus bus_status) {
        this.bus_status = bus_status;
    }

    public void info() {
        System.out.println("버스 번호 = "+this.bus_number);
        System.out.println("버스 운행 상태 = "+this.getBus_status());
        System.out.println("버스 현재 속도 = "+this.getCurrent_speed()+"Km/h");
        System.out.println("버스 현재 주유량 = "+this.getFuel()+"L");
        System.out.println("버스 현재 잔고 = "+this.getBilling()+"원");
        System.out.println("버스 최대 승객수 = "+this.getMax_passenger()+"명");
        System.out.println("버스 현재 승객수 = "+this.getCurrent_passenger()+"명");
        System.out.println("버스 잔여 승객수 = "+(this.getMax_passenger()-this.getCurrent_passenger())+"명");
        if(this.getCurrent_speed()>80){
            System.out.println("");
            System.err.println("현재 속도는 "+this.current_speed+"Km/h입니다. 속도를 낮추세요.");
            System.out.println("");
        }
        if(this.getFuel()<10){
            System.out.println("");
            System.err.println("주유 필요");
            System.out.println("");
        }
        if(this.getCurrent_passenger()<0){
            System.out.println("");
            System.err.println("탑승객의 인원 수가 0보다 작습니다.");
            System.out.println("");
        }
    }
}
