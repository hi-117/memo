package com.memo.app.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.domain.Pageable;

import com.memo.app.common.enums.SearchMatch;
import com.memo.app.entity.TMemos;

@Mapper
public interface TMemosRepository {

	@Select("""
		SELECT
		    id
		    , title
		    , detail
		    , user_id
		    , is_deleted
		    , created_at
		    , updated_at 
		FROM
		    memo.t_memos
		WHERE
			is_deleted = 0  
		ORDER BY
		    id;
			""")
	Collection<TMemos> findAll();
	
	
	@Select("""
			SELECT
			    id
			    , title
			    , detail
			    , user_id
			    , is_deleted
			    , created_at
			    , updated_at 
			FROM
			    memo.t_memos
			WHERE
				is_deleted = 0  
			ORDER BY
			    id
			LIMIT
				#{pageable.pageSize}
			OFFSET
				#{pageable.offset}
				""")
	List<TMemos> findAllPage(@Param("pageable") Pageable pageable);
	
	@Select("""
			SELECT
			    COUNT(*)
			FROM
			    memo.t_memos
			WHERE
				is_deleted = 0;
				""")
	long count();
	
	@Select("""
			<script>
				SELECT
				    id
				    , title
				    , detail
				    , user_id
				    , is_deleted
				    , created_at
				    , updated_at 
				FROM
				    memo.t_memos
				<where>
					<if test="id != null">
						AND id = #{id}
					</if>
					<if test="title != null and title != ''">
						<if test="matchForTitle != null and matchForTitle.getValue() == 1">
							AND title LIKE #{title}
						</if>
						<if test="matchForTitle != null and matchForTitle.getValue() == 2">
							AND title LIKE CONCAT('%', #{title} ,'%')
						</if>
					</if>
					<if test="detail != null and detail != ''">
						<if test="matchForDetail != null and matchForDetail.getValue() == 1">
							AND detail LIKE #{detail}
						</if>
						<if test="matchForDetail != null and matchForDetail.getValue() == 2">
							AND detail LIKE CONCAT('%', #{detail} ,'%')
						</if>
					</if>
					<if test="userId != null">
						AND user_id = #{userId}
					</if>
					AND is_deleted = 0
				</where>
				ORDER BY
				    id;
			</script>
				""")
		Collection<TMemos> findBy(
				Integer id,
				String title,
				String detail,
				Integer userId,
				SearchMatch matchForTitle,
				SearchMatch matchForDetail
				);
	
	@Select("""
			SELECT
			    id
			    , title
			    , detail
			    , user_id
			    , is_deleted
			    , created_at
			    , updated_at 
			FROM
			    memo.t_memos
			WHERE
				id = #{id};
				""")
	Optional<TMemos> findById(int id);
	
	@Insert("""
			INSERT INTO memo.t_memos (
				title
			    , detail
			    , user_id
			) VALUES (
				#{title}
				, #{detail}
				, #{userId}
			);
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void register(TMemos memo);
	
	@Update("""
			UPDATE
				memo.t_memos
			SET
				title = #{title}
			    , detail = #{detail}
			WHERE
				id = #{id}
			""")
	void update(TMemos memo);
	
	@Update("""
			UPDATE
				memo.t_memos
			SET
				is_deleted = 1
			WHERE
				id = #{id}
			""")
	void delete(TMemos memo);
}
