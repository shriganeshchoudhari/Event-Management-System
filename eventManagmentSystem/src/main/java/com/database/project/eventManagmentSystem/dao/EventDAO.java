package com.database.project.eventManagmentSystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.database.project.eventManagmentSystem.event.Event;

@Component("eventDAO")
public class EventDAO {

	private NamedParameterJdbcTemplate jdbc;
	
	/**
	 * @param jdbc the jdbc to set
	 */
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	/**
	 * 
	 * @return
	 */
	public List<Event> getEvents() {
		return jdbc.query("select * from Event", new RowMapper<Event>() { 

			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				Event event = new Event();
				
				event.setId(rs.getInt(1));
				event.setName(rs.getString(2));
				event.setDescription(rs.getString(3));
				event.setAddress(rs.getString(4));
				event.setTotal_seats(rs.getInt(5));
				
				return event;
			}
		});
	}
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public boolean create(Event event){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(event);
		return jdbc.update("insert into Event (name,description,address,total_seats, organized_by) values(:name, :description, :address, :total_seats, :organized_by)", params) == 1;
	}
	
	/**
	 * 
	 * @param event
	 * @param userId
	 */
	public void attend(Integer event_id, Integer numGuests, Integer userId) {
		System.out.println("Event ID: "+event_id);
		System.out.println("User ID: "+userId);
		String SQL = "INSERT INTO Event_Attendee (event_id, noOfGuests, user_id) VALUES (:eventId, :numGuests, :userId)";
	      Map namedParameters = new HashMap();   
	      namedParameters.put("eventId", event_id);   
	      namedParameters.put("userId", userId);
	      namedParameters.put("numGuests", numGuests);
	      jdbc.update(SQL, namedParameters);
	}
	
	/**
	 * 
	 * @param event_id
	 * @param userId
	 */
	public void interested(Integer event_id, Integer userId) {
		System.out.println("Event ID: "+event_id);
		System.out.println("User ID: "+userId);
		String SQL = "INSERT INTO Event_Prospective_Attendee (event_id, user_id) VALUES (:eventId, :userId)";
	      Map namedParameters = new HashMap();   
	      namedParameters.put("eventId", event_id);   
	      namedParameters.put("userId", userId);
	      jdbc.update(SQL, namedParameters);
	}
	
	
	public List<Integer> getAttendees(Integer event_id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("event_id", event_id);
		return jdbc.query("select user_id from Event_Attendee where event_id = :event_id", params, new RowMapper<Integer>() { 
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		});
	}
	
	public List<Integer> getProspectiveAttendees(Integer event_id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("event_id", event_id);
		return jdbc.query("select user_id from Event_Interests where event_id = :event_id", params, new RowMapper<Integer>() { 
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		});
	}
	
	public List<Integer> getEventId(String eventName) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("event_name", eventName);
		return jdbc.query("select id from Event where name = :event_name", params, new RowMapper<Integer>() { 
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		});
	}
	
}
