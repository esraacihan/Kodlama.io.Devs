package kodlama.io.devs.business.concretes;

import kodlama.io.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.business.requests.programmingLanguages.CreateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.programmingLanguages.DeleteProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.programmingLanguages.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.responses.programmingLanguages.GetAllProgrammingLanguagesResponse;
import kodlama.io.devs.business.responses.programmingLanguages.GetByIdProgrammingLanguageResponse;
import kodlama.io.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {
    private ProgrammingLanguageRepository programmingLanguageRepository;

    @Autowired
    public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository) {
        this.programmingLanguageRepository = programmingLanguageRepository;
    }

    @Override
    public List<GetAllProgrammingLanguagesResponse> getAll() {
        List<ProgrammingLanguage> programmingLanguages = this.programmingLanguageRepository.findAll();
        List<GetAllProgrammingLanguagesResponse> getAllProgramingLanguagesResponse = new ArrayList<GetAllProgrammingLanguagesResponse>();

        for (ProgrammingLanguage programmingLanguage : programmingLanguages) {
            GetAllProgrammingLanguagesResponse responseItem = new GetAllProgrammingLanguagesResponse();
            responseItem.setId(programmingLanguage.getId());
            responseItem.setName(programmingLanguage.getName());

            getAllProgramingLanguagesResponse.add(responseItem);
        }

        return getAllProgramingLanguagesResponse;
    }

    @Override
    public GetByIdProgrammingLanguageResponse getById(int id) throws Exception {
        if (!isIdExist(id)) throw new Exception("Id bulunamad??");
        ProgrammingLanguage programmingLanguage = this.programmingLanguageRepository.findById(id).get();
        GetByIdProgrammingLanguageResponse getByIdProgrammingLanguageResponse = new GetByIdProgrammingLanguageResponse();
        getByIdProgrammingLanguageResponse.setName(programmingLanguage.getName());
        return getByIdProgrammingLanguageResponse;
    }

    @Override
    public void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception {
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
        programmingLanguage.setName(createProgrammingLanguageRequest.getName());

        if (isNameExistForAdd(programmingLanguage)) throw new Exception("Bu isimde programlama dili zaten mevcuttur");
        if (isNameEmptyForAdd(programmingLanguage)) throw new Exception("??sim bo?? ge??ilemez");
        this.programmingLanguageRepository.save(programmingLanguage);
    }

    @Override
    public void update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) throws Exception {
        if (!isIdExist(updateProgrammingLanguageRequest.getId())) throw new Exception("Id bulunamad??");

        ProgrammingLanguage programmingLanguage = this.programmingLanguageRepository.findById(updateProgrammingLanguageRequest.getId()).get();


        if (isNameEmptyForUpdate(updateProgrammingLanguageRequest)) throw new Exception("??sim alan?? bo?? ge??ilemez");
        if (isNameExistForUpdate(updateProgrammingLanguageRequest))
            throw new Exception("Bu isimde programlama dili zaten var");

        programmingLanguage.setName(updateProgrammingLanguageRequest.getName());
        this.programmingLanguageRepository.save(programmingLanguage);
    }

    @Override
    public void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) throws Exception {
        if (!isIdExist(deleteProgrammingLanguageRequest.getId())) throw new Exception("Id bulunamad??");
        this.programmingLanguageRepository.deleteById(deleteProgrammingLanguageRequest.getId());
    }

    private boolean isNameExistForAdd(ProgrammingLanguage programmingLanguage) {
        for (GetAllProgrammingLanguagesResponse language : getAll()) {
            if (programmingLanguage.getName().equals(language.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isNameEmptyForAdd(ProgrammingLanguage programmingLanguage) {
        if (programmingLanguage.getName().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isNameEmptyForUpdate(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {
        if (updateProgrammingLanguageRequest.getName().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isNameExistForUpdate(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {
        for (GetAllProgrammingLanguagesResponse language : getAll()) {
            if (updateProgrammingLanguageRequest.getName().equals(language.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isIdExist(int id) {
        for (GetAllProgrammingLanguagesResponse language : getAll()) {
            if (language.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
