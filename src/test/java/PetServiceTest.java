import com.example.pet_shelter.model.db.entity.Pet;
import com.example.pet_shelter.model.enums.PetStatus;
import com.example.pet_shelter.model.db.repository.PetRepository;
import com.example.pet_shelter.service.impl.PetServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PetServiceTest {
    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    @Test
    void getPetsByStatus() {
        Pet pet = new Pet();
        pet.setStatus(PetStatus.LOST);
        when(petRepository.findByStatus(PetStatus.LOST)).thenReturn(List.of(pet));

        List<Pet> result = petService.getPetsByStatus(PetStatus.LOST);
        assertEquals(1, result.size());
        assertEquals(PetStatus.LOST, result.get(0).getStatus());
    }
}
