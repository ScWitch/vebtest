package com.app.init;

import com.app.dtos.BrandsDto;
import com.app.dtos.ModelsDto;
import com.app.dtos.OffersDto;
import com.app.dtos.RolesDto;
import com.app.dtos.UsersDto;
import com.app.enums.Category;
import com.app.enums.Engine;
import com.app.enums.Role;
import com.app.enums.Transmission;
import com.app.models.Users;
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
        RolesDto rolesDto = new RolesDto(Role.ADMIN);
        RolesDto rolesDto1 = new RolesDto(Role.USER);
        rolesService.registerRoles(rolesDto);
        rolesService.registerRoles(rolesDto1);

        UsersDto usersDto = new UsersDto(true,"car@mail.ru", new Date(), "Sinergia", "URL img", "Lyche", new Date(), "Wseh", "Sinergia_net", 1);
        UsersDto usersDto1 = new UsersDto(true,"moto@mail.ru", new Date(), "Mitya", "URL img", "Sinyakov", new Date(), "0000", "Gigant_Misli", 2);
        UsersDto usersDto2 = new UsersDto(true,"sem@mail.ru", new Date(), "Semuil", "URL img", "Dapdap", new Date(), "12345", "Kometa43", 2);
        usersService.registerUsers(usersDto);
        usersService.registerUsers(usersDto1);
        usersService.registerUsers(usersDto2);

        BrandsDto brandsDto = new BrandsDto(new Date(), new Date(), "BMW");
        BrandsDto brandsDto1 = new BrandsDto(new Date(), new Date(), "Avtovazzzzzz");
        brandsService.registerBrands(brandsDto);
        brandsService.registerBrands(brandsDto1);

        ModelsDto modelsDto = new ModelsDto(Category.CAR, new Date(), 2025, "URL img", new Date(), "GT-500", 1945, 1);
        ModelsDto modelsDto1 = new ModelsDto(Category.MOTORCYCLE, new Date(), 2023, "URL img", new Date(), "Speed3000", 2000, 1);
        ModelsDto modelsDto2 = new ModelsDto(Category.BUSS, new Date(), 2020, "URL img", new Date(), "Eleckrobus", 1989, 2);
        modelsService.registerModels(modelsDto);
        modelsService.registerModels(modelsDto1);
        modelsService.registerModels(modelsDto2);

        OffersDto offersDto = new OffersDto(new Date(), "Сомнительное предложение", Engine.ELECTRIC, "Пустая ссылка на картинку", 5700, new Date(), BigDecimal.valueOf(6000), Transmission.AUTOMATIC, 2023,1,1);
        OffersDto offersDto1 = new OffersDto(new Date(), "Предложение получше", Engine.DIESEL, "Пустая ссылка на картинку", 70700, new Date(), BigDecimal.valueOf(6000), Transmission.MANUAL, 2023,3,3);
        OffersDto offersDto2 = new OffersDto(new Date(), "Perfect", Engine.HYBRID, "Пустая ссылка на картинку", 70700, new Date(), BigDecimal.valueOf(6000), Transmission.MANUAL, 2023,2,2);
        offersService.registerOffers(offersDto);
        offersService.registerOffers(offersDto1);
        offersService.registerOffers(offersDto2);
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }
}