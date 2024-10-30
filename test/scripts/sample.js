import http from 'k6/http';
import { sleep } from 'k6';
import { Counter, Gauge, Trend, Rate } from 'k6/metrics';

// 새로운 메트릭 객체 생성
const counter = new Counter('my_counter');      // 카운터 (호출 횟수 추적)
const successRate = new Rate('my_rate');        // 비율 측정 (예: 성공률)
const myTrend = new Trend('my_trend');          // 특정 값의 분포 (예: 응답 시간)
const tpsRate = new Rate('tps_rate');           // TPS 측정을 위한 Rate 메트릭

export const options = {
    scenarios: {
        contacts: {
            executor: 'per-vu-iterations',      // 각 사용자에 대해 지정한 수 만큼 반복 수행하도록 함
            vus: 100,                           // 가상 사용자 수
            iterations: 20,                     // 각 가상 사용자가 반복 수행할 횟수
            maxDuration: '30s',                 // 시나리오의 최대 실행 시간
        },
    },
}

export default function () {
    const response = http.get('https://test.k6.io');
    sleep(1);

    // TPS를 위한 Rate에 각 트랜잭션 추가
    tpsRate.add(1);                             // 매 호출마다 TPS에 트랜잭션 1회 추가

    // 사용자 정의 메트릭 값 추가
    counter.add(1);                             // 카운터 값 추가 (매 호출마다 1씩 증가)
    myTrend.add(response.timings.duration);     // 요청 소요 시간 추가
    successRate.add(response.status === 200);   // 성공 여부를 비율로 추가 (200일 때만 true)
}
