/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.updateerror;

/**
 *
 * @author Shi
 */
public class UpdateErrorDTO {
    private String nameLengthError;
    private String descriptionLengthError;
    private String priceFormatError;
    public UpdateErrorDTO() {
    }

    public UpdateErrorDTO(String nameLengthError, String descriptionLengthError, String priceFormatError) {
        this.nameLengthError = nameLengthError;
        this.descriptionLengthError = descriptionLengthError;
        this.priceFormatError = priceFormatError;

    }

    /**
     * @return the nameLengthError
     */
    public String getNameLengthError() {
        return nameLengthError;
    }

    /**
     * @param nameLengthError the nameLengthError to set
     */
    public void setNameLengthError(String nameLengthError) {
        this.nameLengthError = nameLengthError;
    }

    /**
     * @return the descriptionLengthError
     */
    public String getDescriptionLengthError() {
        return descriptionLengthError;
    }

    /**
     * @param descriptionLengthError the descriptionLengthError to set
     */
    public void setDescriptionLengthError(String descriptionLengthError) {
        this.descriptionLengthError = descriptionLengthError;
    }

    /**
     * @return the priceFormatError
     */
    public String getPriceFormatError() {
        return priceFormatError;
    }

    /**
     * @param priceFormatError the priceFormatError to set
     */
    public void setPriceFormatError(String priceFormatError) {
        this.priceFormatError = priceFormatError;
    }
}
