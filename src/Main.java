import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("진행할 시나리오를 입력하세요.[Bus/Taxi]");
        Scanner sc_start = new Scanner(System.in);
        String choose_scenario = sc_start.nextLine().toLowerCase();

        if("bus".equals(choose_scenario)){
                //Bus 시나리오
            Bus bus;

            int start_stop_button = 1;

            System.out.println("Bus 시나리오를 시작합니다.");
            System.out.println("시나리오를 진행할 버스의 대수를 입력해주세요.");
            Scanner sc_b0 = new Scanner(System.in);
            int number_of_bus = sc_b0.nextInt();

            List<Bus> bus_list = new ArrayList<>();

            for(int i=1; i<number_of_bus+1; i++){
                System.out.println(i+"번째 버스의 최대 탑승 가능한 승객의 인원 수를 입력해주세요.");
                Scanner sc_b1 = new Scanner(System.in);
                int max_passenger = sc_b1.nextInt();
                if (max_passenger <= 0) {
                    System.out.println("최대 탑승 가능한 승객의 인원 수는 0보다 커야합니다. 다시 입력해주세요.");
                    System.out.println("Bus 시나리오를 종료합니다.");
                    start_stop_button *= -1;
                    break;
                }
                System.out.println(i+"번째 버스의 현재 탑승한 승객의 인원 수를 입력해주세요.");
                Scanner sc_b2 = new Scanner(System.in);
                int current_passenger = sc_b2.nextInt();
                if(max_passenger < current_passenger){
                    System.out.println("최대 탑승 가능한 승객의 인원 수를 초과해서 입력했습니다. 다시 입력해주세요.");
                    System.out.println("Bus 시나리오를 종료합니다.");
                    start_stop_button *= -1;
                    break;
                } else if (current_passenger < 0) {
                    System.out.println("현재 탑승한 승객의 인원 수는 0과 같거나 커야합니다. 다시 입력해주세요.");
                    System.out.println("Bus 시나리오를 종료합니다.");
                    start_stop_button *= -1;
                    break;
                }
                System.out.println(i+"번째 버스의 현재 잔고를 입력해주세요. (*버스의 현재 잔고는 0과 같거나 커야합니다.)");
                Scanner sc_b3 = new Scanner(System.in);
                int billing = sc_b3.nextInt();
                if(billing < 0){
                    System.out.println("버스의 현재 잔고는 0과 같거나 커야합니다.");
                    System.out.println("Bus 시나리오를 종료합니다.");
                    start_stop_button *= -1;
                    break;
                }
                System.out.println(i+"번째 버스의 현재 주유량을 입력해주세요. (*버스가 운행하려면 주유량은 10보다 커야합니다.)");
                Scanner sc_b4 = new Scanner(System.in);
                int fuel = sc_b4.nextInt();
                if(fuel < 10){
                    System.out.println("버스가 운행하려면 주유량은 10보다 커야합니다.");
                    System.out.println("Bus 시나리오를 종료합니다.");
                    start_stop_button *= -1;
                    break;
                }
                System.out.println(i+"번째 버스의 현재 속도를 입력해주세요. (*버스가 운행하려면 버스의 속도는 0과 같거나 커야합니다.)");
                Scanner sc_b5 = new Scanner(System.in);
                int current_speed = sc_b5.nextInt();
                if(current_speed < 0){
                    System.out.println("버스가 운행하려면 버스의 속도는 0과 같거나 커야합니다.");
                    System.out.println("Bus 시나리오를 종료합니다.");
                    start_stop_button *= -1;
                    break;
                }
                bus_list.add(new Bus(max_passenger, current_passenger, billing, fuel, current_speed));

                System.out.println("");
            }

            for(int j=0; j<bus_list.size(); j++){
                System.out.println("["+(j+1)+"번째 bus의 정보]");
                bus_list.get(j).info();
                System.out.println("");
            }

            if(start_stop_button > 0){
                System.out.println("시나리오를 진행할 버스의 번호를 입력해주세요.");
                Scanner sc_b1 = new Scanner(System.in);
                String bus_number = sc_b1.nextLine();
                for(int k=0; k<bus_list.size(); k++){
                    if(bus_list.get(k).getBus_number().equals(bus_number)){
                        bus = bus_list.get(k);
                        while(true){
                            System.out.println("버스 운행을 계속하려면 'start'을 입력해주세요.");
                            System.out.println("버스 운행을 종료하고 차고지로 돌아가려면 'stop'를 입력해주세요.");
                            System.out.println("버스 운행 시니라오 종료를 원한다면 'esc'를 입력해주세요.");
                            Scanner sc_b2 = new Scanner(System.in);

                            if(sc_b2.nextLine().equals("start")){
                                bus.setBus_status(BusStatus.RUN);
                            } else if (sc_b2.nextLine().equals("stop")) {
                                bus.setBus_status(BusStatus.GARAGE);
                            } else if (sc_b2.nextLine().equals("esc")) {
                                System.out.println("버스 운행 시니라오가 종료되었습니다.");
                                System.out.println("이용해주셔서 감사합니다.");
                                break;
                            } else {
                                System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
                                continue;
                            }

                            if(bus.getBus_status().equals(BusStatus.RUN)){
                                System.out.println("버스의 증가하거나 감소한 속도를 입력해주세요. (속도가 감소했다면 음수로 입력하세요. 단,버스의 속도는 0보다 커야합니다.)");
                                Scanner sc_b3 = new Scanner(System.in);
                                bus.setCurrent_speed(sc_b3.nextInt());
                                System.out.println("버스정류장에서 버스에 탑승한 승객의 인원 수를 입력해주세요.");
                                Scanner sc_b4 = new Scanner(System.in);
                                int run_process1 = sc_b4.nextInt();
                                bus.setCurrent_passenger(run_process1);
                                bus.setBilling(run_process1*1000);
                                bus.info();
                                System.out.println("");
                                System.out.println("버스가 운행하면서 기름이 감소했습니다.");
                                System.out.println("감소한 주유량을 입력해주세요.");
                                Scanner sc_b5 = new Scanner(System.in);
                                bus.setFuel(-sc_b5.nextInt());
                                System.out.println("버스정류장에서 하차한 승객의 인원 수를 입력해주세요.");
                                Scanner sc_b11 = new Scanner(System.in);
                                bus.setCurrent_passenger(-sc_b11.nextInt());
                                System.out.println("");
                                bus.info();
                                System.out.println("");
                                if(bus.getFuel()<10 | bus.getCurrent_passenger()<0) {
                                    System.err.println("버스의 주유량이 10L보다 작거나 탑승객의 수가 0명보다 작습니다. 운행을 종료합니다.");
                                    System.out.println("초기화가 진행중입니다.");
                                    System.out.println("");
                                    bus.setBus_status(BusStatus.RESET);
                                    bus.setCurrent_speed(0);
                                    bus.setFuel(100);
                                    bus.setBilling(0);
                                    bus.setCurrent_passenger(0);
                                    bus.info();
                                    System.out.println("");
                                    System.out.println("초기화가 완료되었습니다.");
                                    System.out.println("");
                                }
                                continue;
                            } else if (bus.getBus_status().equals(BusStatus.GARAGE)) {
                                System.out.println("");
                                System.out.println("버스가 차고지에 도착했습니다.");
                                System.out.println("버스가 차고지에 도착하기 전에 승객은 전부 내렸습니다.");
                                bus.info();

                                System.out.println("");
                                System.out.println("차고지에서 주유한 기름의 양을 입력해주세요.");
                                Scanner sc_b6 = new Scanner(System.in);
                                bus.setFuel(sc_b6.nextInt());
                                bus.info();
                                continue;
                            } else {
                                System.out.println("");
                                System.err.println("5XX 서버 에러");
                                break;
                            }
                        }
                    }
                    continue;
                }
            }
        } else if ("taxi".equals(choose_scenario)) {
            //Taxi 시나리오
            Taxi taxi;

            int start_stop_button = 1;
            int try_count = 0;
            int try_size = 0;
            int toggle = 1;
            int toggle_pay = 0;

            System.out.println("Taxi 시나리오를 시작합니다.");
            System.out.println("시나리오를 진행할 택시의 대수를 입력해주세요.");
            Scanner sc_b0 = new Scanner(System.in);
            int number_of_taxi = sc_b0.nextInt();

            List<Taxi> taxi_list = new ArrayList<>();

            for(int i=1; i<number_of_taxi+1; i++){
                System.out.println(i+"번째 택시의 최대 탑승 가능한 승객의 인원 수를 입력해주세요.");
                Scanner sc_b1 = new Scanner(System.in);
                int max_passenger = sc_b1.nextInt();
                if (max_passenger <= 0) {
                    System.out.println("최대 탑승 가능한 승객의 인원 수는 0보다 커야합니다. 다시 입력해주세요.");
                    System.out.println("Taxi 시나리오를 종료합니다.");
                    start_stop_button *= -1;
                    break;
                }

                taxi_list.add(new Taxi(max_passenger));
                try_size = taxi_list.size();
                System.out.println("");
            }

            for(int j=0; j<taxi_list.size(); j++){
                System.out.println("["+(j+1)+"번째 taxi의 정보]");
                taxi_list.get(j).taxi_info();
                System.out.println("");
            }

            if(start_stop_button > 0){
                System.out.println("시나리오를 진행할 택시의 번호를 입력해주세요.");
                Scanner sc_b1 = new Scanner(System.in);
                String taxi_number = sc_b1.nextLine();
                for(int k=0; k<taxi_list.size(); k++){
                    try_count++;
                    if(taxi_list.get(k).getTaxi_number().equals(taxi_number)){
                        taxi = taxi_list.get(k);
                        while(true){
                            System.out.println("택시 운행을 계속하려면 'start'을 입력해주세요.");
                            System.out.println("택시에서 손님을 내리고 계산하려면 'pay'을 입력해주세요. (*손님이 전부 하차해야 'start'를 진행할 수 있습니다.)");
                            System.out.println("택시 운행을 종료하고 손님을 대기하거나 주유를 원하면 'stop'을 입력해주세요.");
                            System.out.println("택시 운행 시니라오 종료를 원한다면 'esc'를 입력해주세요.");
                            System.out.println("현재 택시의 전체 정보를 원한다면 '#'를 입력해주세요.");
                            System.out.println("현재 택시의 요약 정보를 원한다면 '*'를 입력해주세요.");

                            Scanner sc_b2 = new Scanner(System.in);

                            if(sc_b2.nextLine().equals("start")){
                                toggle_pay = 1;
                                if(toggle == 1){
                                    taxi.setTaxi_status(TaxiStatus.RUN);
                                    taxi.setTaxi_destination("");
                                }else{
                                    System.err.println("손님이 "+taxi.getTaxi_current_passenger()+"명 남아 있습니다. 다시 돌아가서 'pay'를 입력해주세요.");
                                    System.out.println("");
                                    continue;
                                }
                            } else if (sc_b2.nextLine().equals("pay")) {
                                if(toggle_pay > 0){
                                    taxi.setTaxi_status(TaxiStatus.PAY);
                                    taxi.setTaxi_destination("입력된 정보 없음");
                                } else {
                                    System.err.println("택시에 탑승한 승객이 없습니다. 다시 돌아가서 'start'를 입력해주세요.");
                                    taxi.setTaxi_destination("");
                                    continue;
                                }
                            } else if (sc_b2.nextLine().equals("stop")) {
                                taxi.setTaxi_status(TaxiStatus.NORMAL);
                                taxi.setTaxi_destination("입력된 정보 없음");
                            } else if (sc_b2.nextLine().equals("esc")) {
                                taxi.setTaxi_destination("입력된 정보 없음");
                                System.out.println("택시 운행 시니라오가 종료되었습니다.");
                                System.out.println("이용해주셔서 감사합니다.");
                                break;
                            } else if (sc_b2.nextLine().equals("#")) {
                                System.out.println("");
                                taxi.taxi_info();
                                System.out.println("");
                                continue;
                            } else if (sc_b2.nextLine().equals("*")) {
                                System.out.println("");
                                taxi.taxi_info_summary();
                                System.out.println("");
                                continue;
                            } else {
                                System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
                                continue;
                            }

                            if(taxi.getTaxi_status().equals(TaxiStatus.RUN)){
                                System.out.println("택시에 탑승한 승객의 인원 수를 입력해주세요.");
                                Scanner sc_b3 = new Scanner(System.in);
                                taxi.setTaxi_current_passenger(sc_b3.nextInt());
                                System.out.println("승객의 목적지를 입력해주세요.");
                                Scanner sc_b4 = new Scanner(System.in);
                                taxi.setTaxi_destination(sc_b4.nextLine());
                                System.out.println("현재 택시에서 목적지까지의 거리를 입력해주세요.");
                                Scanner sc_b5 = new Scanner(System.in);
                                int run_process1 = sc_b5.nextInt();
                                taxi.setTaxi_destination_distance(run_process1);
                                System.out.println("현재 택시의 증가하거나 감소한 속도를 입력해주세요. (속도가 감소했다면 음수로 입력하세요. 단,택시의 속도는 0보다 커야합니다.)");
                                Scanner sc_b6 = new Scanner(System.in);
                                taxi.setTaxi_current_speed(sc_b6.nextInt());
                                taxi.setCustomer_billing((run_process1-1)*taxi.getTaxi_fee_per_distance()+taxi.getTaxi_default_fee());
                                System.out.println("");
                                System.out.println("택시가 운행중입니다~");
                                System.out.println("");
                                System.out.println("택시가 운행하면서 기름이 감소했습니다.");
                                System.out.println("감소한 주유량을 입력해주세요.");
                                Scanner sc_b7 = new Scanner(System.in);
                                taxi.setTaxi_fuel(-sc_b7.nextInt());
                                System.out.println("");
                                if(taxi.getTaxi_fuel()<10 | taxi.getTaxi_current_passenger()<0) {
                                    System.err.println("택시의 주유량이 10L보다 작거나 승객의 수가 0명보다 작습니다. 운행을 종료합니다.");
                                    System.out.println("초기화가 진행중입니다~");
                                    taxi.setTaxi_status(TaxiStatus.RESET);
                                    taxi.setTaxi_current_speed(0);
                                    taxi.setTaxi_fuel(100);
                                    taxi.setTaxi_billing(0);
                                    taxi.setCustomer_billing(0);
                                    taxi.setTaxi_current_passenger(0);
                                    System.out.println("");
                                    System.out.println("초기화가 완료되었습니다.");
                                    System.out.println("");
                                } else if (taxi.getTaxi_current_passenger() > taxi.getTaxi_max_passenger()) {
                                    System.err.println("현재 승객의 수가 탑승 가능한 최대 승객 수를 초과합니다. 운행을 종료합니다.");
                                    System.out.println("초기화가 진행중입니다~");
                                    taxi.setTaxi_status(TaxiStatus.RESET);
                                    taxi.setTaxi_current_speed(0);
                                    taxi.setTaxi_fuel(100);
                                    taxi.setTaxi_billing(0);
                                    taxi.setCustomer_billing(0);
                                    taxi.setTaxi_current_passenger(0);
                                    System.out.println("");
                                    System.out.println("초기화가 완료되었습니다.");
                                    System.out.println("");
                                }
                                toggle = 0;
                                continue;
                            } else if (taxi.getTaxi_status().equals(TaxiStatus.PAY)) {
                                System.out.println("");
                                System.out.println("택시를 길가에 세웠습니다~");
                                System.out.println("");
                                System.out.println("길가에 하차한 승객의 인원 수를 입력해주세요.");
                                Scanner sc_b11 = new Scanner(System.in);
                                int pay_process1 = sc_b11.nextInt();
                                taxi.setTaxi_current_passenger(-pay_process1);
                                System.out.println("손님이 "+pay_process1+"명 하차했습니다. "+"현재 남은 승객의 인원 수는 "+taxi.getTaxi_current_passenger()+"명 입니다.");
                                System.out.println("");

                                while(taxi.getTaxi_current_passenger()>0){
                                    System.out.println("최종 목적지를 다시 한 번 입력해주세요.");
                                    Scanner sc_b4 = new Scanner(System.in);
                                    String pay_process2 = sc_b4.nextLine();

                                    while(taxi.getTaxi_current_passenger()>0) {
                                        System.out.println("현재 택시 승객수는 "+taxi.getTaxi_current_passenger()+"명 입니다.");
                                        taxi.setTaxi_destination(pay_process2);
                                        System.out.println("출발지에서 목적지까지의 거리를 다시 확인하고 입력해주세요.");
                                        Scanner sc_b12 = new Scanner(System.in);
                                        int pay_process3 = sc_b12.nextInt();
                                        taxi.setTaxi_destination_distance(pay_process3);
                                        System.out.println("현재 택시의 증가하거나 감소한 속도를 입력해주세요. (속도가 감소했다면 음수로 입력하세요. 단,택시의 속도는 0보다 커야합니다.)");
                                        Scanner sc_b14 = new Scanner(System.in);
                                        taxi.setTaxi_current_speed(sc_b14.nextInt());
                                        taxi.setCustomer_billing((pay_process3-1)*taxi.getTaxi_fee_per_distance()+taxi.getTaxi_default_fee());
                                        System.out.println("");
                                        System.out.println("택시가 운행중입니다~");
                                        System.out.println("");
                                        System.out.println("택시가 운행하면서 기름이 감소했습니다.");
                                        System.out.println("감소한 주유량을 입력해주세요.");
                                        Scanner sc_b15 = new Scanner(System.in);
                                        taxi.setTaxi_fuel(-sc_b15.nextInt());
                                        System.out.println("");
                                        System.out.println("택시를 길가에 세웠습니다~");
                                        System.out.println("");
                                        System.out.println("길가에 하차한 승객의 인원 수를 입력해주세요.");
                                        Scanner sc_b17 = new Scanner(System.in);
                                        taxi.setTaxi_current_passenger(-sc_b17.nextInt());
                                        System.out.println("");
                                    }

                                    if(taxi.getTaxi_fuel()<10 | taxi.getTaxi_current_passenger()<0) {
                                        System.err.println("택시의 주유량이 10L보다 작거나 승객의 수가 0명보다 작습니다. 운행을 종료합니다.");
                                        System.out.println("초기화가 진행중입니다~");
                                        taxi.setTaxi_status(TaxiStatus.RESET);
                                        taxi.setTaxi_current_speed(0);
                                        taxi.setTaxi_fuel(100);
                                        taxi.setTaxi_billing(0);
                                        taxi.setCustomer_billing(0);
                                        taxi.setTaxi_current_passenger(0);
                                        System.out.println("");
                                        System.out.println("초기화가 완료되었습니다.");
                                        System.out.println("");
                                        break;
                                    }
                                }
                                if(taxi.getTaxi_fuel()<10 | taxi.getTaxi_current_passenger()<0){
                                    taxi.setTaxi_status(TaxiStatus.NORMAL);
                                    toggle = 1;
                                    break;
                                }

                                System.out.println("");
                                System.out.println("주유소에서 기름을 채웠다면 주유한 기름의 양을 입력해주세요.");
                                Scanner sc_b8 = new Scanner(System.in);
                                taxi.setTaxi_fuel(sc_b8.nextInt());
                                System.out.println("");
                                System.out.println("택시의 기본 거리당 발생하는 요금을 인상/인하하고 싶은 만큼 값을 입력하세요. (*원치 않는다면 0을 입력하세요.)");
                                Scanner sc_b9 = new Scanner(System.in);
                                taxi.setTaxi_fee_per_distance(sc_b9.nextInt());
                                System.out.println("");
                                taxi.setTaxi_billing(taxi.getCustomer_billing());
                                taxi.setCustomer_billing(0);
                                taxi.setTaxi_status(TaxiStatus.NORMAL);
                                taxi.taxi_after_payment_info();
                                toggle = 1;
                                toggle_pay = 0;
                                continue;
                            } else if (taxi.getTaxi_status().equals(TaxiStatus.NORMAL)) {
                                System.out.println("");
                                System.out.println("택시를 길가에 세웠습니다.");
                                System.out.println("손님을 대기합니다.");
                                System.out.println("");
                                System.out.println("주유소에서 기름을 채웠다면 주유한 기름의 양을 입력해주세요.");
                                Scanner sc_b8 = new Scanner(System.in);
                                taxi.setTaxi_fuel(sc_b8.nextInt());
                                System.out.println("");
                                System.out.println("택시의 기본 거리당 발생하는 요금을 인상/인하하고 싶은 만큼 값을 입력하세요. (*원치 않는다면 0을 입력하세요.)");
                                Scanner sc_b9 = new Scanner(System.in);
                                taxi.setTaxi_fee_per_distance(sc_b9.nextInt());
                                System.out.println("");
                                taxi.setTaxi_status(TaxiStatus.NORMAL);
                                toggle = 1;
                                toggle_pay = 0;
                                continue;
                            } else {
                                System.out.println("");
                                System.err.println("5XX 서버 에러");
                                taxi.setTaxi_status(TaxiStatus.NORMAL);
                                toggle = 1;
                                break;
                            }
                        }
                    } else if(try_count>=try_size) {
                        System.out.println("");
                        System.out.println("택시의 번호를 잘못 입력했거나 없는 택시 번호입니다.");
                        System.out.println("택시의 기본 정보를 처음부터 다시 입력해주세요.");
                        System.out.println("4XX 고객의 입력 에러");
                        break;
                    }
                }
            }
        } else {
            System.out.println("잘못된 입력입니다. 시스템을 종료합니다.");
        }
    }
}
