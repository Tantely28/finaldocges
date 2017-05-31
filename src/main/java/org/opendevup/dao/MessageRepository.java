package org.opendevup.dao;

import java.util.Date;
import java.util.List;

import org.opendevup.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//spring data is ORM (Object Relationnel Mapping)

//that's all : save , findall="selectall" with pageble, getOne, delete, ...
public interface MessageRepository extends JpaRepository<Message, Long>{
	//add another method - spring , we will understand
		public List<Message> findByTitle(String title);
		//message page on page
		public Page<Message> findByTitle(String title,Pageable pageable);
		
		//new method don't convention,search Message with key, add @Query
		@Query("select m from Message m where m.title like :x")
		public Page<Message> searchMessage(@Param("x")String key_word,Pageable pageable);
		
		//new method don't convention,search message between to date add @Query
		@Query("select m from Message m where m.date_message >:x and m.date_message < :y")
		public List<Message> searchMessage(@Param("x")Date d1,@Param("y")Date d2);
		
		//new method don't convention,search message between to date add @Query
		@Query("select m from Message m order by m.date_message desc")
		public Page<Message> listMessage(Pageable pageable);
}
