package flab.docdoc.review.repository;


import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.review.domain.HospitalStatistics;
import flab.docdoc.review.domain.Review;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReviewRepository {

    public Review findByUniqueId(Long reviewUniqueId);

    public int save(Review review);

    public int update(Review review);

    public int delete(final Long reviewUniqueId);

}
