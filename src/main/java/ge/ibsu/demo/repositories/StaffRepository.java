package ge.ibsu.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ge.ibsu.demo.entities.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

	@Query("FROM Staff WHERE concat(concat(firstName, ' '), lastName) LIKE :searchText")
	Page<Staff> searchStaffs(@Param("searchText") String searchText, Pageable pageable);
}
