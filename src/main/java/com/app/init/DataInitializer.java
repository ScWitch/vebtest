package com.app.init;

import com.app.dtos.BrandsDto;
import com.app.dtos.ModelsDto;
import com.app.dtos.OffersDto;
import com.app.dtos.RolesDto;
import com.app.dtos.UsersDto;
import com.app.enums.Category;
import com.app.enums.Engine;
import com.app.enums.Transmission;
import com.app.services.BrandsServices;
import com.app.services.ModelsService;
import com.app.services.OffersService;
import com.app.services.RolesService;
import com.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {
    private final OffersService<Integer> offersService;
    private final ModelsService<Integer> modelsService;
    private final UsersService<Integer> usersService;
    private final RolesService<Integer> rolesService;
    private final BrandsServices<Integer> brandsService;

    @Autowired
    public DataInitializer(
            OffersService<Integer> offersService,
            ModelsService<Integer> modelsService,
            UsersService<Integer> usersService,
            RolesService<Integer> rolesService,
            BrandsServices<Integer> brandsService
    ) {
        this.offersService = offersService;
        this.modelsService = modelsService;
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.brandsService = brandsService;
    }

    private void seedData() {
        Transmission trf = Transmission.AUTOMATIC;
        Engine engine = Engine.ELECTRIC;
        OffersDto offersDto = new OffersDto(new Date(), "Учшее предложение", engine.toString(), "URL изображения", 10000, new Date(), BigDecimal.valueOf(5000), trf.toString(), 2023);
        offersService.registerOffers(offersDto);
        UsersDto usersDto = new UsersDto(true, new Date(),"Имя", "URL изображения", "Фамилия",new Date(),"parol","Пароль",1,"adssada");
        usersService.registerUsers(usersDto);
        Category cat = Category.CAR;
        ModelsDto modelsDto = new ModelsDto(cat.toString(), new Date(), 2025, "URL изображения модели", new Date(), "Модель автомобиля", 2020, 1);
        modelsService.registerModels(modelsDto);
        BrandsDto brandsDto = new BrandsDto(new Date(), new Date(), "Название бренда");
        brandsService.registerBrands(brandsDto);
        RolesDto rolesDto = new RolesDto("Admin");
        rolesService.registerRoles(rolesDto);
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }
}