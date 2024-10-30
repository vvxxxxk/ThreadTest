import http from 'k6/http';
import { sleep } from 'k6';
import { Trend } from 'k6/metrics';

const myTrend = new Trend('waiting_time');

export const options = {
    scenarios: {
        webflux: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '1m', target: 100 },   // 1분 동안 100 VU까지 증가
                { duration: '2m', target: 500 },   // 2분 동안 500 VU까지 증가
                { duration: '2m', target: 1000 },  // 2분 동안 1000 VU까지 증가
                { duration: '3m', target: 2000 },  // 3분 동안 2000 VU 유지
                { duration: '3m', target: 0 },     // 3분 동안 0 VU로 감소
            ],
            gracefulStop: '20s', // 종료 시 20초 동안 완료될 시간 확보
        }
    },
    thresholds: {
        http_req_failed: ['rate<0.05'],     // 실패율을 5% 미만으로 유지
        http_req_duration: ['p(95)<3000'],  // 95% 요청이 3초 이내에 완료
    },
    http: {
        timeout: '5s',  // 타임아웃을 60초로 설정
    }
};

export default function () {
    const url = `http://localhost:10002/api/v1/webflux/basic/${__VU}`;
    const response = http.post(url);
    myTrend.add(response.timings.waiting);
    sleep(1);  // 요청 사이에 1초 대기 추가
}
