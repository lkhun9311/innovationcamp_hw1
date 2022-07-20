import java.util.UUID;

public class Bus {
    private int max_passenger; // 최대 승객수
    private int current_passenger; // 현재 승객수
    private int billing; // 요금
    private final String bus_number; // 버스 번호;
    private int fuel;
    private int current_speed;
    private BusStatus bus_status;

    public Bus(int max_passenger, int current_passenger, int billing, int fuel, int current_speed){
        this.max_passenger = max_passenger;
        this.current_passenger = current_passenger;
        this.billing = billing;
        this.bus_number = UUID.randomUUID().toString(); // UUID를 활용해 버스 번호에 고유한 값을 입력
        this.fuel = fuel;
        this.current_speed = current_speed;
        this.bus_status = BusStatus.RUN;

        if(this.fuel < 10){
            this.bus_status = BusStatus.GARAGE;
            System.out.println("주유 필요");
        }

    }

    public int getMax_passenger() {
        return max_passenger;
    }

    public void setMax_passenger(int max_passenger) {
        this.max_passenger = max_passenger;
    }

    public int getCurrent_passenger() {
        return current_passenger;
    }

    public void setCurrent_passenger(int current_passenger) {
        this.current_passenger = current_passenger;
    }

    public int getBilling() {
        return billing;
    }

    public void setBilling(int billing) {
        this.billing = billing;
    }

    public String getBus_number() {
        return bus_number;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getCurrent_speed() {
        return current_speed;
    }

    public void setCurrent_speed(int current_speed) {
        this.current_speed = current_speed;
    }

    public BusStatus getBus_status() {
        return bus_status;
    }

    public void setBus_status(BusStatus bus_status) {
        this.bus_status = bus_status;
    }

    public void info() {
        System.out.println("버스 번호 = "+this.bus_number);
        System.out.println("버스 운행 상태 = "+this.bus_status);
        System.out.println("버스 현재 속도 = "+this.current_speed);
        System.out.println("버스 현재 주유량 = "+this.fuel);
        System.out.println("버스 현재 잔고 = "+this.billing);
        System.out.println("버스 최대 승객수 = "+this.max_passenger);
        System.out.println("버스 현재 승객수 = "+this.current_passenger);
    }
}
