package com.example.kiemtra.service.Impl;

import com.example.kiemtra.dto.DiscountDTO;
import com.example.kiemtra.entity.Discount;
import com.example.kiemtra.exception.NotFoundException;
import com.example.kiemtra.repostitory.DiscountRepository;
import com.example.kiemtra.service.DiscountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Discount> getAllDiscount() {
        return discountRepository.findAll();
    }

    public void addDiscount(DiscountDTO discountDTO){
            if(checkDiscountExsist(discountDTO.getDiscountName()))
            {
                Discount discount = new Discount();
                modelMapper.map(discountDTO,discount);
                discountRepository.save(discount);
            }
            else
                throw new NotFoundException("Không thể thêm vì vé giảm giá đã tồn tại");
    }

    public void updateDiscountById(Long discountId ,DiscountDTO discountDTO)
    {
        Optional<Discount> discount = discountRepository.findById(discountId);
        if(discount.isEmpty())
            throw new NotFoundException(("Không tìm thấy discount cần sửa"));
        modelMapper.map(discountDTO,discount.get());
        discountRepository.save(discount.get());
    }

    public void deleteDiscountById(Long discountId)
    {
        Optional<Discount> discount = discountRepository.findById(discountId);
        if(discount.isEmpty())
            throw new NotFoundException("Không tìm thấy vé giảm giá để xóa");
        discountRepository.deleteById(discountId);
    }

    public Boolean checkDiscountExsist(String discountName)
    {
        Optional<Discount> discount = Optional.ofNullable(discountRepository.findByDiscountName(discountName));
        return discount.isEmpty();
    }

    public Boolean checkDiscountExistsById(Long id)
    {
        Optional<Discount> discount = discountRepository.findById(id);
        return discount.isPresent();
    }

}
