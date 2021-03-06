package com.database.project.eventManagmentSystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("participantDAO")
public class ParticipantDAO {

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
	public List<Participant> getParticipants() {
		return jdbc.query("select * from Participant", new RowMapper<Participant>() { 

			public Participant mapRow(ResultSet rs, int rowNum) throws SQLException {
				Participant participant = new Participant();
				
				participant.setId(rs.getInt(1));
				participant.setName(rs.getString(2));
				participant.setEmail(rs.getString(3));
				participant.setPhone(rs.getString(4));
				participant.setZipcode(rs.getString(5));
				
				return participant;
			}
		});
	}
	
	/**
	 * 
	 * @return
	 */
	public Participant getParticipant(int id) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.queryForObject("select * from Participant where id=:id", params, new RowMapper<Participant>() { //wrap this in Prepared Statement later

			public Participant mapRow(ResultSet rs, int rowNum) throws SQLException {
				Participant participant = new Participant();
				
				participant.setId(rs.getInt(1));
				participant.setName(rs.getString(2));
				participant.setEmail(rs.getString(3));
				participant.setPhone(rs.getString(4));
				participant.setZipcode(rs.getString(5));
				
				return participant;
			}
		});
	}
	
	/**
	 * 
	 * @return
	 */
	public Participant getParticipant(String name) {
		System.out.println("name in getParticipant"+name);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", name);
		return jdbc.queryForObject("select * from Participant where name=:name", params, new RowMapper<Participant>() { //wrap this in Prepared Statement later

			public Participant mapRow(ResultSet rs, int rowNum) throws SQLException {
				Participant participant = new Participant();
				
				participant.setId(rs.getInt(1));
				participant.setName(rs.getString(2));
				participant.setEmail(rs.getString(3));
				participant.setPhone(rs.getString(4));
				participant.setZipcode(rs.getString(5));
				
				return participant;
			}
		});
	}
	
	/**
	 * 
	 * @param participant
	 * @return
	 */
	public Participant create(Participant participant) throws SQLException {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(participant);
		jdbc.update("insert into Participant (name,password,email,phone,zipcode) values(:name, :password, :email, :phone, :zipcode)", params);
		participant = jdbc.queryForObject("select id, name, isAdmin from Participant where email=:email and phone=:phone",params, new RowMapper<Participant>() {
			
			public Participant mapRow(ResultSet rs, int rowNum) throws SQLException {
				Participant p = new Participant();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setIsAdmin(rs.getInt(3));
				return p;
			}
		});
		return participant;
	}
	
	/**
	 * Batch create method
	 * @param participants
	 * @return
	 */
	@Transactional
	public int[] create(List<Participant> participants){
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(participants.toArray());
		return jdbc.batchUpdate("insert into Participant (name,password,email,phone,zipcode) values(:name, :password, :email, :phone, :zipcode)", params);
	}
	
	/**
	 * 
	 * @param participant
	 * @return
	 */
	public boolean update(Participant participant){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(participant);
		return jdbc.update("update Participant set name=:name, password=:password, email=:email, phone=:phone, zipcode=:zipcode where id=:id" , params) == 1;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		return jdbc.update("delete from Participant where id=:id", params) == 1;
	}
}
