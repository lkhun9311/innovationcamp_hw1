import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Bus
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
    }

        //Taxi
}
