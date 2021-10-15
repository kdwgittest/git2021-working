package com.git.myworkspace.opendata.air;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirSigunguHourRepository extends JpaRepository<AirSigunguHour, Long> {

	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

	// ��) �ʵ�� cityName
	// findByCityName
	// -> WHERE city_name = :city ORDER BY ... LIMIT ...
	List<AirSigunguHour> findByCityName(Pageable page, String city);
}