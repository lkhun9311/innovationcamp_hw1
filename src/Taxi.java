import java.util.UUID;

public class Taxi {

//Bus와 동일한 요소들
    private int taxi_max_passenger; // 택시의 탑승 가능한 최대 승객 인원 수
    private int taxi_current_passenger; // 택시의 탑승 가능한 현재 승객 인원 수
    private int taxi_billing; // 택시의 잔고
    private int customer_billing; // 손님에게 받을 요금
    private final String taxi_number; // 택시 번호;
    private int taxi_fuel; // 주유량
    private int taxi_current_speed; // 택시의 현재 속도
    private TaxiStatus taxi_status; // 택시의 운행 상태

//Bus에는 없는 Taxi만의 요소들
    private String taxi_destination; // 택시의 목적지
    private int taxi_destination_distance; // 택시의 목적지까지의 거리
    private int taxi_fee_per_distance; // 택시의 거리당 발생하는 요금
    private final int taxi_default_distance; // 택시의 기본 제공 거리
    private final int taxi_default_fee; // 택시의 기본 요금

    public Taxi(int taxi_max_passenger){
        this.taxi_max_passenger = taxi_max_passenger; // 택시의 기본 최대 승객 수 4명
        this.taxi_current_passenger = 0; // 택시의 기본 현재 탑승객 수 0명
        this.taxi_billing = 0; // 택시의 기본 잔고 0원
        this.customer_billing = 0; // 손님에게 받을 요금 0원
        this.taxi_number = UUID.randomUUID().toString(); // UUID를 활용해 택시 번호에 고유한 값을 입력
        this.taxi_fuel = 100; // 택시의 기본 주유량 100L
        this.taxi_current_speed = 0; // 택시의 기본 속도 0km/h
        this.taxi_status = TaxiStatus.NORMAL; // 택시의 기본 상태 "NORMAL(일반)"

        this.taxi_destination = "입력된 정보 없음"; // 택시의 기본 목적지 "입력된 정보 없음"으로 초기화
        this.taxi_destination_distance = 0; // 택시의 목적지까지의 거리 0으로 초기화
        this.taxi_fee_per_distance = 1000; // 택시의 기본 거리당 발생하는 요금 1000원
        this.taxi_default_distance = 1; // 택시의 기본 거리 1km
        this.taxi_default_fee = 3000; // 택시의 기본 요금 3000원

    }

    // getter, setter
    public int getTaxi_max_passenger() {
        return taxi_max_passenger;
    }

    public int getTaxi_current_passenger() {
        return taxi_current_passenger;
    }

    public void setTaxi_current_passenger(int taxi_current_passenger) {
        this.taxi_current_passenger += taxi_current_passenger;
        if(this.taxi_current_passenger>this.getTaxi_max_passenger()){
            System.out.println("");
            System.err.println("최대 승객 수 초과");
            System.out.println("");
        } else if (this.taxi_current_passenger<0) {
            System.out.println("");
            System.err.println("5XX 서버 에러 ");
            System.err.println("승객 수는 음수가 될 수 없습니다.");
            System.out.println("");
        }
        if(this.taxi_status.equals(TaxiStatus.RESET)|this.taxi_status.equals(TaxiStatus.NORMAL)){
            this.taxi_current_passenger = 0;
        }
    }

    public int getTaxi_billing() {
        return taxi_billing;
    }

    public void setTaxi_billing(int taxi_billing) {
        this.taxi_billing += taxi_billing;
        if(this.taxi_status.equals(TaxiStatus.RESET)){
            this.taxi_billing = 0;
        }
    }

    public int getCustomer_billing() {
        return customer_billing;
    }

    public void setCustomer_billing(int customer_billing) {
        this.customer_billing = customer_billing;
        if(this.taxi_status.equals(TaxiStatus.RESET)){
            this.customer_billing = 0;
        }
    }

    public String getTaxi_number() {
        return taxi_number;
    }

    public int getTaxi_fuel() {
        return taxi_fuel;
    }

    public void setTaxi_fuel(int taxi_fuel) {
        this.taxi_fuel += taxi_fuel;
        if(this.taxi_status.equals(TaxiStatus.RESET)){
            this.taxi_fuel = 100;
        }
    }

    public int getTaxi_current_speed() {
        return taxi_current_speed;
    }

    public void setTaxi_current_speed(int taxi_current_speed) {
        this.taxi_current_speed += taxi_current_speed;
        if(this.taxi_status.equals(TaxiStatus.RESET)|this.taxi_status.equals(TaxiStatus.NORMAL)){
            this.taxi_current_speed = 0;
        }
    }

    public TaxiStatus getTaxi_status() {
        return taxi_status;
    }

    public void setTaxi_status(TaxiStatus taxi_status) {
        this.taxi_status = taxi_status;
    }

    public String getTaxi_destination() {
        return taxi_destination;
    }

    public void setTaxi_destination(String taxi_destination) {
            this.taxi_destination = taxi_destination;
    }

    public int getTaxi_destination_distance() {
        return taxi_destination_distance;
    }

    public void setTaxi_destination_distance(int taxi_destination_distance) {
        this.taxi_destination_distance = taxi_destination_distance;
    }

    public int getTaxi_fee_per_distance() {
        return taxi_fee_per_distance;
    }

    public void setTaxi_fee_per_distance(int taxi_fee_per_distance) {
        this.taxi_fee_per_distance += taxi_fee_per_distance;
        if((this.taxi_default_fee+this.taxi_fee_per_distance)<=0){
            System.out.println("");
            System.err.println("운행 요금이 0과 같거나 작습니다.");
            System.out.println("");
        }
    }

    public int getTaxi_default_distance() {
        return taxi_default_distance;
    }

    public int getTaxi_default_fee() {
        return taxi_default_fee;
    }

    //Taxi의 전체 정보
    public void taxi_info() {
        System.out.println("택시 번호 = "+this.getTaxi_number());
        System.out.println("택시 최대 승객수 = "+this.getTaxi_max_passenger()+"명");
        System.out.println("택시 현재 승객수 = "+this.getTaxi_current_passenger()+"명");
        System.out.println("택시 잔여 승객수 = "+(this.getTaxi_max_passenger()-this.getTaxi_current_passenger())+"명");
        System.out.println("택시 현재 속도 = "+this.getTaxi_current_speed()+"km/h");
        System.out.println("택시 현재 주유량 = "+this.getTaxi_fuel()+"L");
        System.out.println("목적지 = "+this.getTaxi_destination());
        System.out.println("목적지까지의 거리 = "+this.getTaxi_destination_distance()+"km");
        System.out.println("택시 기본 제공 거리(요금 계산에서 제외) = "+this.getTaxi_default_distance()+"km");
        System.out.println("택시 기본 요금 = "+this.getTaxi_default_fee()+"원");
        System.out.println("택시의 기본 거리당 발생하는 요금 = "+this.getTaxi_fee_per_distance()+"원");
        System.out.println("손님에게 받을 요금 = "+this.getCustomer_billing()+"원");
        System.out.println("택시 현재 잔고 = "+this.getTaxi_billing()+"원");
        System.out.println("택시 운행 상태 = "+this.getTaxi_status());
        if(this.getTaxi_current_passenger()>this.getTaxi_max_passenger()){
            System.out.println("");
            System.err.println("현재 택시 승객수는 "+this.getTaxi_current_passenger()+"명입니다. 현재 택시의 최대 탑승 가능한 승객수는 "+this.getTaxi_max_passenger()+"명 입니다.");
            System.out.println("");
        }
        if(this.getTaxi_current_speed()>80){
            System.out.println("");
            System.err.println("현재 속도는 "+this.getTaxi_current_speed()+"km/h입니다. 속도를 낮추세요.");
            System.out.println("");
        }
        if(this.getTaxi_fuel()<10){
            System.out.println("");
            System.err.println("현재 주유량은 "+this.getTaxi_fuel()+"L입니다. 주유가 필요합니다");
            System.out.println("");
        }
    }

    //Taxi의 요약 정보
    public void taxi_info_summary() {
        System.out.println("택시 현재 승객수 = "+this.getTaxi_current_passenger()+"명");
        System.out.println("택시 현재 속도 = "+this.getTaxi_current_speed()+"km/h");
        System.out.println("택시 현재 주유량 = "+this.getTaxi_fuel()+"L");
        System.out.println("목적지 = "+this.getTaxi_destination());
        System.out.println("목적지까지의 거리 = "+this.getTaxi_destination_distance()+"km");
        System.out.println("손님에게 받을 요금 = "+this.getCustomer_billing()+"원");
        System.out.println("택시 현재 잔고 = "+this.getTaxi_billing()+"원");
        if(this.getTaxi_current_passenger()>this.getTaxi_max_passenger()){
            System.out.println("");
            System.err.println("현재 택시 승객수는 "+this.getTaxi_current_passenger()+"명입니다. 현재 택시의 최대 탑승 가능한 승객수는 "+this.getTaxi_max_passenger()+"명 입니다.");
            System.out.println("");
        }
        if(this.getTaxi_current_speed()>80){
            System.out.println("");
            System.err.println("현재 속도는 "+this.getTaxi_current_speed()+"km/h입니다. 속도를 낮추세요.");
            System.out.println("");
        }
        if(this.getTaxi_fuel()<10){
            System.out.println("");
            System.err.println("현재 주유량은 "+this.getTaxi_fuel()+"L입니다. 주유가 필요합니다");
            System.out.println("");
        }
    }

    //Taxi의 손님 결제 후 정보
    public void taxi_after_payment_info() {
        System.out.println("현재 위치 = "+this.getTaxi_destination());
        System.out.println("택시 현재 승객수 = "+this.getTaxi_current_passenger()+"명");
        System.out.println("택시 현재 주유량 = "+this.getTaxi_fuel()+"L");
        System.out.println("택시 현재 잔고 = "+this.getTaxi_billing()+"원");
        System.out.println("택시 운행 상태 = "+this.getTaxi_status());
        if(this.getTaxi_fuel()<10){
            System.out.println("");
            System.err.println("현재 주유량은 "+this.getTaxi_fuel()+"L입니다. 주유가 필요합니다");
            System.out.println("");
        }
    }
}