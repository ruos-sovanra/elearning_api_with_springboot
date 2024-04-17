package org.project.spring_mini_project.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Authority;
import org.project.spring_mini_project.domain.City;
import org.project.spring_mini_project.domain.Country;
import org.project.spring_mini_project.domain.Role;
import org.project.spring_mini_project.features.authority.AuthoritiesRepository;
import org.project.spring_mini_project.features.city.CityRepository;
import org.project.spring_mini_project.features.country.CountryRepository;
import org.project.spring_mini_project.features.role.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class DataInitialize {
    private final RoleRepository roleRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    @PostConstruct
    void init() {
        roleInit();
        authorityInit();
        roleAuthorityInit();
        cityInit();
        countryInit();
    }

    void roleInit(){
        List<String> roles = List.of("ADMIN", "INSTRUCTOR", "STUDENT","USER");
        if (roleRepository.count() == 0){
            roles.forEach(role -> {
                Role newRole = new Role();
                newRole.setName(role);
                roleRepository.save(newRole);
            });
        }
    }

    void authorityInit(){
        List<String> authorities = List.of("user:read", "user:write", "user:delete","user:update","progress:read","progress:write","elearning:read","elearning:write","elearning:udpate","elearning:delete");
        if (authoritiesRepository.count() == 0){
            authorities.forEach(authority ->{
                Authority newAuthority = new Authority();
                newAuthority.setName(authority);
                authoritiesRepository.save(newAuthority);
            });
        }
    }

    void roleAuthorityInit(){
        // Fetch all roles and authorities
        Set<Role> roles = new HashSet<>(roleRepository.findAll());
        Set<Authority> authorities = new HashSet<>(authoritiesRepository.findAll());

        // Assign authorities to roles
        for (Role role : roles) {
            try {
                switch (role.getName()) {
                    case "USER":
                        role.setAuthorities(filterAuthorities(authorities, "user:read", "user:write", "user:update","progress:read","elearning:read"));
                        break;
                    case "STUDENT":
                        role.setAuthorities(filterAuthorities(authorities, "progress:write"));
                        break;
                    case "INSTRUCTOR":
                        role.setAuthorities(filterAuthorities(authorities, "user:read", "user:write", "user:update","progress:read","elearning:read",  "elearning:read", "elearning:write", "elearning:delete", "elearning:update"));
                        break;
                    case "ADMIN":
                        role.setAuthorities(authorities); // Admin has all authorities
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected role: " + role.getName());
                }
                // Save the role back to the repository
                roleRepository.save(role);
            } catch (Exception e) {
                System.out.println("Error assigning authorities to role: " + role.getName());
                e.printStackTrace();
            }
        }
    }

    private Set<Authority> filterAuthorities(Set<Authority> authorities, String... names) {
        Set<Authority> filtered = new HashSet<>();
        for (Authority authority : authorities) {
            for (String name : names) {
                if (authority.getName().equals(name)) {
                    filtered.add(authority);
                }
            }
        }
        return filtered;
    }

    void countryInit(){
        List<Country> countries = new ArrayList<>(){{
            add(new Country().setFlag("https://restcountries.com/data/afg.svg").setIso("AF").setName("Afghanistan").setNice_name("Afghanistan").setNum_code(4).setPhone_code(93));
            add(new Country().setFlag("https://restcountries.com/data/brn.svg").setIso("BN").setName("Brunei").setNice_name("Brunei").setNum_code(96).setPhone_code(673));
            add(new Country().setFlag("https://restcountries.com/data/khm.svg").setIso("KH").setName("Cambodia").setNice_name("Cambodia").setNum_code(116).setPhone_code(855));
            add(new Country().setFlag("https://restcountries.com/data/idn.svg").setIso("ID").setName("Indonesia").setNice_name("Indonesia").setNum_code(360).setPhone_code(62));
            add(new Country().setFlag("https://restcountries.com/data/lao.svg").setIso("LA").setName("Laos").setNice_name("Laos").setNum_code(418).setPhone_code(856));
            add(new Country().setFlag("https://restcountries.com/data/mys.svg").setIso("MY").setName("Malaysia").setNice_name("Malaysia").setNum_code(458).setPhone_code(60));
            add(new Country().setFlag("https://restcountries.com/data/mmr.svg").setIso("MM").setName("Myanmar").setNice_name("Myanmar").setNum_code(104).setPhone_code(95));
            add(new Country().setFlag("https://restcountries.com/data/phl.svg").setIso("PH").setName("Philippines").setNice_name("Philippines").setNum_code(608).setPhone_code(63));
            add(new Country().setFlag("https://restcountries.com/data/sgp.svg").setIso("SG").setName("Singapore").setNice_name("Singapore").setNum_code(702).setPhone_code(65));
            add(new Country().setFlag("https://restcountries.com/data/tha.svg").setIso("TH").setName("Thailand").setNice_name("Thailand").setNum_code(764).setPhone_code(66));
            add(new Country().setFlag("https://restcountries.com/data/vnm.svg").setIso("VN").setName("Vietnam").setNice_name("Vietnam").setNum_code(704).setPhone_code(84));
        }};
        if (countryRepository.count() == 0){
            countryRepository.saveAll(countries);
        }
    }

    void cityInit(){
        List<Country> countries = countryRepository.findAll();
        List<City> cities = new ArrayList<>(){{
            add(new City().setName("Kabul").setCountry(countries.get(0)));
            add(new City().setName("Kandahar").setCountry(countries.get(0)));
            add(new City().setName("Herat").setCountry(countries.get(0)));

            add(new City().setName("Bandar Seri Begawan").setCountry(countries.get(1)));
            add(new City().setName("Kuala Belait").setCountry(countries.get(1)));
            add(new City().setName("Seria").setCountry(countries.get(1)));

            add(new City().setName("Phnom Penh").setCountry(countries.get(2)));
            add(new City().setName("Battambang").setCountry(countries.get(2)));
            add(new City().setName("Siem Reap").setCountry(countries.get(2)));

            add(new City().setName("Jakarta").setCountry(countries.get(3)));
            add(new City().setName("Surabaya").setCountry(countries.get(3)));
            add(new City().setName("Bandung").setCountry(countries.get(3)));

            add(new City().setName("Vientiane").setCountry(countries.get(4)));
            add(new City().setName("Luang Prabang").setCountry(countries.get(4)));
            add(new City().setName("Pakse").setCountry(countries.get(4)));

            add(new City().setName("Kuala Lumpur").setCountry(countries.get(5)));
            add(new City().setName("George Town").setCountry(countries.get(5)));
            add(new City().setName("Ipoh").setCountry(countries.get(5)));

            add(new City().setName("Naypyidaw").setCountry(countries.get(6)));
            add(new City().setName("Yangon").setCountry(countries.get(6)));
            add(new City().setName("Mandalay").setCountry(countries.get(6)));

            add(new City().setName("Manila").setCountry(countries.get(7)));
            add(new City().setName("Davao City").setCountry(countries.get(7)));
            add(new City().setName("Cebu City").setCountry(countries.get(7)));

            add(new City().setName("Singapore").setCountry(countries.get(8)));

            add(new City().setName("Bangkok").setCountry(countries.get(9)));
            add(new City().setName("Pattaya").setCountry(countries.get(9)));
            add(new City().setName("Phuket").setCountry(countries.get(9)));

            add(new City().setName("Hanoi").setCountry(countries.get(10)));
            add(new City().setName("Ho Chi Minh City").setCountry(countries.get(10)));
            add(new City().setName("Da Nang").setCountry(countries.get(10)));




            // ... add more cities for each country as needed
        }};
        if (cityRepository.count() == 0){
            cityRepository.saveAll(cities);
        }
    }

}
