import React from 'react';
import { Autocomplete, TextField } from '@mui/material';
import { fieldToTextField } from 'formik-material-ui';


const FormikAutocomplete = ({ textFieldProps, ...props }) => {

  const { form: { setTouched, setFieldValue } } = props;
  const { error, helperText, ...field } = fieldToTextField(props);
  const { name } = field;

  return (
    <Autocomplete
      {...props}
      {...field}
      onChange={ (_, value) => setFieldValue(name, value) }
      onBlur={ () => setTouched({ [name]: true }) }
      getOptionLabel={(option) => option.villageName}
      renderInput={ props => (
        <TextField {...props} {...textFieldProps} helperText={helperText} error={error} />
      )}
    />
  );
}

export default FormikAutocomplete;