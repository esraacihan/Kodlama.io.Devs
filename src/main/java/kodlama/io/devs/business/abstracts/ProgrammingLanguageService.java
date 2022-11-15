package kodlama.io.devs.business.abstracts;

import kodlama.io.devs.business.requests.programmingLanguages.CreateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.programmingLanguages.DeleteProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.programmingLanguages.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.responses.programmingLanguages.GetAllProgrammingLanguagesResponse;
import kodlama.io.devs.business.responses.programmingLanguages.GetByIdProgrammingLanguageResponse;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;

import java.util.List;

public interface ProgrammingLanguageService {
    List<GetAllProgrammingLanguagesResponse> getAll();
    GetByIdProgrammingLanguageResponse getById(int id) throws Exception;
    void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception;
    void update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) throws Exception;
    void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) throws Exception;

}
