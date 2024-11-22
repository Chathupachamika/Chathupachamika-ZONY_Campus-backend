package edu.icet.service.impl;

import edu.icet.dto.Register;
import edu.icet.entity.RegisterEntity;
import edu.icet.repository.RegisterRepository;
import edu.icet.service.RegisterService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private final RegisterRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<Register> getAll() {
        List<Register> customerArrayList = new ArrayList<>();
        repository.findAll().forEach(entity->{
            customerArrayList.add(mapper.map(entity, Register.class));
        });
        return customerArrayList;
    }

    @Override
    public void addUser(Register user, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            user.setImageName(imageFile.getOriginalFilename());
            user.setImageType(imageFile.getContentType());
            user.setImageData(imageFile.getBytes());
        }
        RegisterEntity entity = mapper.map(user, RegisterEntity.class);
        repository.save(entity);
    }

    @Override
    public Register searchUserById(Long id) {
        Optional<RegisterEntity> entity = repository.findById(id);
        return entity.map(e -> mapper.map(e, Register.class)).orElse(null);
    }

    @Override
    public void deleteUserById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public RegisterEntity findByEmail(String email) {
        return repository.findByEmail(email);
    }
    @Override
    public void updateUserById(Register user, MultipartFile imageFile) throws IOException {
        Optional<RegisterEntity> existingEntityOpt = repository.findById(user.getReg_id());

        if (existingEntityOpt.isPresent()) {
            RegisterEntity existingEntity = existingEntityOpt.get();

            existingEntity.setProgram(user.getProgram());
            existingEntity.setTitle(user.getTitle());
            existingEntity.setReferral(user.getReferral());
            existingEntity.setShortName(user.getShortName());
            existingEntity.setFullName(user.getFullName());
            existingEntity.setDob(user.getDob());
            existingEntity.setCountry(user.getCountry());
            existingEntity.setPassport(user.getPassport());
            existingEntity.setMobile(user.getMobile());
            existingEntity.setEmail(user.getEmail());
            existingEntity.setAddress(user.getAddress());

            existingEntity.setUsername(user.getUsername());
            existingEntity.setPassword(user.getPassword());

            if (imageFile != null && !imageFile.isEmpty()) {
                existingEntity.setImageName(imageFile.getOriginalFilename());
                existingEntity.setImageType(imageFile.getContentType());
                existingEntity.setImageData(imageFile.getBytes());
            }

            repository.save(existingEntity);
        } else {
            throw new EntityNotFoundException("User with ID " + user.getReg_id() + " not found");
        }
    }

    @Override
    public List<RegisterEntity> getLastUsers(int count) {
        Pageable pageable = PageRequest.of(0, count);
        return repository.findByOrderByCreatedDateDesc(pageable);
    }

}

