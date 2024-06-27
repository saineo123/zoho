package projectManagementSystem.projectManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectManagementSystem.projectManagement.model.Admin;
import projectManagementSystem.projectManagement.repository.AdminRepository;
@Service
public class AdminService {
    @Autowired
    AdminRepository admin_repo;


    public <S extends Admin> S save(final S entity){
        return admin_repo.save(entity);
    }
    
    public Optional<Admin> findName(final Long aLong){
        return admin_repo.findById(aLong);
    }

    public Iterable<Admin> findAll(){
        return admin_repo.findAll();
    }
    
    public void deleteById(final Long aLong){
        admin_repo.deleteById(aLong);
    }

    public boolean existsById(final Long aLong){
        return admin_repo.existsById(aLong);
    }
}
