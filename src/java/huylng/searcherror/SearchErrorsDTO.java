/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.searcherror;

import java.io.Serializable;

/**
 *
 * @author Shi
 */
public class SearchErrorsDTO implements Serializable{
    private String EndNumberAvailableError;
    private String BeginNumberFormatError;
    private String EndNumberFormatError;

    public SearchErrorsDTO() {
    }

    public SearchErrorsDTO(String EndNumberAvailableError, String BeginNumberFormatError, String EndNumberFormatError) {
        this.EndNumberAvailableError = EndNumberAvailableError;
        this.BeginNumberFormatError = BeginNumberFormatError;
        this.EndNumberFormatError = EndNumberFormatError;
    }



    /**
     * @return the EndNumberAvailableError
     */
    public String getEndNumberAvailableError() {
        return EndNumberAvailableError;
    }

    /**
     * @param EndNumberAvailableError the EndNumberAvailableError to set
     */
    public void setEndNumberAvailableError(String EndNumberAvailableError) {
        this.EndNumberAvailableError = EndNumberAvailableError;
    }

    /**
     * @return the BeginNumberFormatError
     */
    public String getBeginNumberFormatError() {
        return BeginNumberFormatError;
    }

    /**
     * @param BeginNumberFormatError the BeginNumberFormatError to set
     */
    public void setBeginNumberFormatError(String BeginNumberFormatError) {
        this.BeginNumberFormatError = BeginNumberFormatError;
    }

    /**
     * @return the EndNumberFormatError
     */
    public String getEndNumberFormatError() {
        return EndNumberFormatError;
    }

    /**
     * @param EndNumberFormatError the EndNumberFormatError to set
     */
    public void setEndNumberFormatError(String EndNumberFormatError) {
        this.EndNumberFormatError = EndNumberFormatError;
    }

    /**
     * @return the BeginNumberError
     */
    
}
