package user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import user.bean.UserDTO;

public class UserDAOMybatis implements UserDAO {
	@Autowired
	private SqlSession sqlSession;
		
	@Transactional
	@Override
	public void write(UserDTO userDTO) {
		sqlSession.insert("userSQL.write",userDTO);
		
	}


	@Override
	public List<UserDTO> getUserList() {
		return sqlSession.selectList("userSQL.getUserList");
		
	}


	@Override
	public List<UserDTO> getUser(UserDTO userDTO) {
		return sqlSession.selectList("userSQL.getuser",userDTO);
	}

	@Transactional
	@Override
	public void update(UserDTO userDTO) {
		sqlSession.update("userSQL.update",userDTO);
		
	}

	@Transactional
	@Override
	public void delete(String id) {
		sqlSession.delete("userSQL.delete",id);
		
	}


	@Override
	public List<UserDTO> search(UserDTO userDTO) {
		
		List<UserDTO> list = sqlSession.selectList("userSQL.search", userDTO);
		return list;
	}

}
