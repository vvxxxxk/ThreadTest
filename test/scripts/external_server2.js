import http from 'k6/http';
import { sleep } from 'k6';
import { Trend } from 'k6/metrics';

const myTrend = new Trend('waiting_time');

export const options = {
    scenarios: {
        contacts: {
            executor: 'per-vu-iterations',      // 각 사용자에 대해 지정한 수 만큼 반복 수행하도록 함
            vus: 20,                             // 가상 사용자 수
            iterations: 1,                     // 각 가상 사용자가 반복 수행할 횟수
            maxDuration: '60s',                 // 시나리오의 최대 실행 시간
        },
    },
}

export default function () {
    const url = `http://localhost:10001/api/v1/external/loop/${__VU}`;
    const response = http.get(url);
    myTrend.add(response.timings.waiting);
    // sleep(1);
}
