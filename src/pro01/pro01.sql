/*
1) EMPLOYEE 테이블에서 이름(Last Name)에 “hae”를 포함하고 있는 사원들과 같은 부서에서 근무하고 있는 사원의 
EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DEPARTMENT_ID 를 출력하라. 
*/

select b.employee_id, b.first_name, b.last_name, b.department_id
from ( select last_name, department_id
       from employees
       where last_name like '%hae%' ) a , employees b
where a.department_id = b.department_id
order by employee_id desc;

/*
2) 각 도시(city)별 가장 연봉을 많이 받고 있는 사원의 도시 이름(City), First Name, Last Name, 급여를 조회하라. 
급여 순으로 오름차순 정렬하시오. (1-2.sql)
*/  

select city, first_name, last_name, salary
from ( select max(salary) ms, location_id li
       from employees e, departments d
       where e.department_id=d.department_id
       group by location_id ) a, employees e, departments d, locations l
where a.ms=e.salary and e.department_id=d.department_id and d.location_id=l.location_id and a.li=d.location_id
order by salary asc;
