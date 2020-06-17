package com.uniovi.repositories;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uniovi.entities.Friendship;
import com.uniovi.entities.User;

public interface FriendsRepository extends CrudRepository<Friendship, Long>
{
	Page<User> findAll(Pageable pageable);
	
	@Query("select f.user2 from Friendship f where f.user1 = ?1 and f.isRequest=FALSE")
	Page<User> findFriendsOf(Pageable pageable, User user1);
	
	@Query("select f.user2 from Friendship f where f.user1 = ?1 and f.isRequest=TRUE")
	Page<User> findFriendRequestsOf(Pageable pageable, User user1);
	
	@Query("select f from Friendship f where f.user1 = ?1 and f.user2 = ?2")
	Set<Friendship> findByUser1andUser2(User user1, User user2);
	
	@Transactional
	@Modifying
	@Query("update Friendship f set f.isRequest = false where f=?1")
	void acceptFriendship(Friendship f);

	@Transactional
	@Modifying
	@Query("delete Friendship f where f=?1")
	void deleteFriendship(Friendship f);
}