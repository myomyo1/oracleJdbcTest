/*
1)	평균 연봉(salary)이 가장 높은 나라는?
 */

select country_name as 나라이름, mas as 평균연봉
from ( select max(avg(salary)) mas 
       from employees e, departments d, locations l, countries c
       where e.department_id=d.department_id and d.location_id = l.location_id and l.country_id=c.country_id
       group by c.country_id ) a, ( select c.country_id, avg(salary) avs
                                    from employees e, departments d, locations l, countries c
                                    where e.department_id=d.department_id and d.location_id = l.location_id and l.country_id=c.country_id
                                    group by c.country_id ) b , employees e, departments d, locations l, countries c
where e.department_id=d.department_id and d.location_id = l.location_id and l.country_id=c.country_id and a.mas=b.avs and b.country_id=c.country_id;

/*
2)	평균 연봉(salary)이 가장 높은 지역은?
*/

select region_name as 지역이름 , b.mas as 평균연봉
from ( select location_id, avg(salary) avs
       from employees e, departments d
       where e.department_id=d.department_id
       group by location_id ) a, ( select max(avg(salary)) mas
                                   from employees e, departments d
                                   where e.department_id=d.department_id 
                                   group by location_id )b, locations l, countries c, regions r
where a.avs= b.mas and a.location_id=l.location_id and l.country_id=c.country_id and c.region_id=r.region_id;


/*
3)	가장 많은 직원이 있는 부서는 어떤 부서인가요?
*/

select d.department_name as 부서이름 , mcn as 직원수
from ( select department_id, count(employee_id) cn
       from employees
       group by department_id ) a , ( select max(count(employee_id)) mcn
                                      from employees
                                      group by department_id) b, departments d
where a.cn=b.mcn and d.department_id=a.department_id; 